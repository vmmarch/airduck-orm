package com.brouck.session;

import com.brouck.annotation.Table;
import com.brouck.exception.IllegalTableClassException;
import com.brouck.exception.SearchNotFoundException;
import com.brouck.generator.table.TableGenerator;
import com.brouck.session.metadata.MetaDataQuery;
import com.brouck.session.metadata.MySQLMetaDataQuery;
import com.brouck.session.metadata.TableMetaData;
import com.brouck.session.sql.SQLGenerator;
import com.brouck.session.wrapper.Query;
import com.brouck.session.wrapper.Update;
import com.brouck.tools.BrouckUtils;

import java.util.*;

/**
 * sql session封装类，这个类一定是被管理在容器中的。必须要持久化在
 * 内存中。
 *
 * @author brouck
 * Create time 2022/3/25
 */
public class BrouckSession {

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
    public BrouckSession(SqlSession sqlSession) {
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
     * 手动添加表元数据
     *
     * @param entityClass 实体类
     */
    public void addTableMetaData(Class<?> entityClass) {
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
        BrouckUtils.checkObject(object);
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
        return sqlSession.openTransaction(session -> session.executeUpdateBatch(sql, args), false); // 批量处理打开事务
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

        return new Query<>(sqlSession, getTableMetaData(table.name()));
    }

    /**
     * 创建更新对象
     */
    public <T> Update<T> createUpdate(Class<T> _class) {
        Table table = _class.getAnnotation(Table.class);
        if (table == null)
            throw new IllegalTableClassException("创建更新失败，实体类必须存在@Table注解。");

        return new Update<>(sqlSession, getTableMetaData(table.name()), _class);
    }

    private TableMetaData getTableMetaData(String name) {
        // 查询表元数据
        TableMetaData tableMetaData = tableMetaDataMap.get(name);
        if (tableMetaData == null)
            throw new SearchNotFoundException("未找到当前表的元数据信息，当前表名：{}", name);

        return tableMetaData;
    }

}
