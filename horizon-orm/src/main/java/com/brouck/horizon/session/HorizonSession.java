package com.brouck.horizon.session;

import com.brouck.horizon.annotation.Table;
import com.brouck.horizon.commons.Reflections;
import com.brouck.horizon.exception.IllegalTableClassException;
import com.brouck.horizon.exception.SearchNotFoundException;
import com.brouck.horizon.generator.table.TableGenerator;
import com.brouck.horizon.session.metadata.MetaDataQuery;
import com.brouck.horizon.session.metadata.MySQLMetaDataQuery;
import com.brouck.horizon.session.metadata.TableMetaData;
import com.brouck.horizon.session.sql.SQLGenerator;
import com.brouck.horizon.tools.HorizonUtils;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * horizon sql session封装类，这个类一定是被管理在容器中的。必须要持久化在
 * 内存中。
 *
 * @author brouck
 * Create time 2022/3/25
 */
public class HorizonSession {

    /**
     * SQLSession对象
     */
    private final SqlSession sqlSession;

    /**
     * 元数据查询
     */
    private final MetaDataQuery metaDataQuery;

    /**
     * 表信息元数据
     */
    private final Map<String, TableMetaData> tableMetaDataMap = new HashMap<>();

    /**
     * 创建HorizonSqlSession
     */
    public HorizonSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.metaDataQuery = new MySQLMetaDataQuery(this);
    }

    /**
     * 获取元数据查询接口
     */
    public MetaDataQuery getMetaDataQuery() {
        return metaDataQuery;
    }

    /**
     * 创建一条记录对象
     */
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public <T> T createRecord(Class<T> _class) {
        HorizonUtils.includeSuperEntity(_class);
        Constructor<?> constructor = _class.getConstructor();
        Object object = constructor.newInstance();
        // 获取设置HorizonSession的方法
        Method setHorizonSession =
                Reflections.searchMethods(_class, "setHorizonSession", HorizonSession.class);
        setHorizonSession.setAccessible(true);
        setHorizonSession.invoke(object, this);

        return (T) object;
    }

    /**
     * 创建多条记录
     */
    public <T> Records<T> createRecords(Class<T> _class, int size) {
        // 循环size创建records
        Records<T> records = new Records<>();
        for (int i = 0; i < size; i++) {
            records.add(createRecord(_class));
        }
        return records;
    }

    /**
     * 手动添加表元数据
     *
     * @param entityClass 实体类
     */
    public void addTableMetaData(Class<?> entityClass) {
        HorizonUtils.includeSuperEntity(entityClass);
        TableMetaData tableMetaData = new TableMetaData(entityClass);
        tableMetaDataMap.put(tableMetaData.getName(), tableMetaData);
    }

    /**
     * 生成表
     */
    public void executeGenerateTable() {
        TableGenerator.generate(this, new ArrayList<>(tableMetaDataMap.values()));
    }

    /**
     * 使用sql单个对象查询
     *
     * @param sql    查询sql
     * @param _class 查询后封装的类
     */
    public <T> T objectQuery(String sql, Class<T> _class) {
        return sqlSession.openTransaction(session -> session.objectQuery(sql, _class), false);
    }

    /**
     * 多个对象查询
     *
     * @param sql    查询sql
     * @param _class 查询后封装的类
     */
    public <T> List<T> listQuery(String sql, Class<T> _class) {
        return sqlSession.openTransaction(session -> session.listQuery(sql, _class), false);
    }

    /**
     * 保存单个对象
     *
     * @param object 需要保存到数据库的对象
     * @return 是否保存成功
     */
    public boolean store(Object object) {
        HorizonUtils.checkObject(object);
        var sqlScript = SQLGenerator.insert(object, tableMetaDataMap);
        return store(sqlScript.getSql(), sqlScript.getParams()) > 0;
    }

    /**
     * 批量保存多个对象
     *
     * @param collections 需要保存到数据库的对象
     * @return 是否保存成功
     */
    public <E> boolean store(Collection<E> collections) {
        if (collections.isEmpty()) {
            return true;
        }
        var sqlScript = SQLGenerator.insert(collections, tableMetaDataMap);
        return store(sqlScript.getSql(), sqlScript.getBatchParams()).length > 0;
    }

    /**
     * 新增数据
     *
     * @param sql  更新sql
     * @param args 更新参数
     */
    public int store(String sql, Object... args) {
        return update(sql, args);
    }

    /**
     * 批量新增数据
     *
     * @param sql  更新sql
     * @param args 批量更新的参数
     */
    public int[] store(String sql, List<Object[]> args) {
        return update(sql, args);
    }

    /**
     * 根据主键删除记录
     */
    public boolean remove(Object object) {
        return false;
    }

    /**
     * 更新单个对象
     *
     * @param object 需要更新到数据库的对象
     * @return 是否更新成功
     */
    public boolean update(Object object) {
        HorizonUtils.checkObject(object);
        return store("", "") > 0;
    }

    /**
     * 更新数据
     *
     * @param sql  更新sql
     * @param args 更新参数
     */
    public int update(String sql, Object... args) {
        return sqlSession.openTransaction(session -> session.executeUpdate(sql, args), false);
    }

    /**
     * 批量更新数据
     *
     * @param sql  更新sql
     * @param args 批量更新的参数
     */
    public int[] update(String sql, List<Object[]> args) {
        return sqlSession.openTransaction(session -> session.executeUpdateBatch(sql, args), true); // 批量处理打开事务
    }


    /**
     * 执行ddl等语句
     *
     * @param sql sql语句
     */
    public void execute(String sql) {
        sqlSession.openTransaction(session -> session.execute(sql), false);
    }

    /**
     * 创建查询对象
     */
    public <T> Query<T> createQuery(Class<T> _class) {
        Table table = _class.getAnnotation(Table.class);
        if (table == null)
            throw new IllegalTableClassException("创建查询失败，实体类必须存在@Table注解。");

        // 查询表元数据
        TableMetaData tableMetaData = tableMetaDataMap.get(table.name());
        if (tableMetaData == null)
            throw new SearchNotFoundException("未找到当前表的元数据信息，当前表名：{}", table.name());

        return new Query<>(sqlSession, tableMetaData);
    }

}
