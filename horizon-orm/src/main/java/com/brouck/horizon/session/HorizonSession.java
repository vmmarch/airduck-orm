package com.brouck.horizon.session;

import com.brouck.horizon.annotation.Table;
import com.brouck.horizon.exception.IllegalTableClassException;
import com.brouck.horizon.exception.SearchNotFoundException;
import com.brouck.horizon.session.metadata.MetaDataQuery;
import com.brouck.horizon.session.metadata.MySQLMetaDataQuery;
import com.brouck.horizon.session.metadata.TableMetaData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * horizon sql session封装类，这个类一定是被管理在容器中的。必须要持久化在
 * 内存中。
 *
 * @author lts
 * Create time 2022/3/25
 */
public class HorizonSession {

    /**
     * SQLSession对象
     */
    private final SqlSession sqlSession;

    /**
     * 数据库名
     */
    private String database;

    /**
     * 元数据查询
     */
    private MetaDataQuery metaDataQuery;

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
     * 打开事务
     */
    public void openTransaction() {
        sqlSession.openSqlSession(true);
    }

    /**
     * 关闭事务
     */
    public void closeTransaction() {
        sqlSession.closeSqlSession();
    }

    /**
     * 手动添加表元数据
     *
     * @param entityClass 实体类
     */
    public void addTableMetaData(Class<?> entityClass) {
        TableMetaData tableMetaData = new TableMetaData(entityClass);
        tableMetaDataMap.put(tableMetaData.getTableName(), tableMetaData);
    }

    /**
     * 使用sql单个对象查询
     *
     * @param hql    查询sql
     * @param _class 查询后封装的类
     */
    public <T> T objectQuery(String hql, Class<T> _class) {
        return sqlSession.openTransaction(session -> session.objectQuery(hql, _class), false);
    }

    /**
     * 多个对象查询
     *
     * @param hql    查询sql
     * @param _class 查询后封装的类
     */
    public <T> List<T> listQuery(String hql, Class<T> _class) {
        return sqlSession.openTransaction(session -> session.listQuery(hql, _class), false);
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
