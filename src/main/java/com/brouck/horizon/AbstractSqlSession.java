package com.brouck.horizon;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author brouck
 * Create time 2022/3/22
 */
public class AbstractSqlSession implements SqlSession {

    private final Configuration configuration;

    /** 当前使用的数据库链接 */
    private Connection currentConnection;

    public AbstractSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void setConnectionContext(Connection connection) {
        this.currentConnection = connection;
    }

    @Override
    public void openSqlSession() {
        try {
            this.currentConnection = configuration.openConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeSqlSession() {
        try {
            configuration.closeConnection(this.currentConnection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> T queryForObject(String sql, Class<T> _class, Object... args) {
        PrecompiledStatement statement = new PrecompiledStatement(sql, args, this.currentConnection);
        ExecuteQuerySet executeQuerySet = statement.executeQuery();
        return executeQuerySet.asObject(_class);
    }

    @Override
    public <T> List<T> queryForList(String sql, Class<T> _class, Object... args) {
        return null;
    }

    @Override
    public int update(Object updateObject, String whereSql) {
        return 0;
    }

    @Override
    public int updateBatch(List<Object> updateObjects, String whereSql) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Object updateObject) {
        return 0;
    }

    @Override
    public int updateBatchByPrimaryKey(List<Object> updateObjects) {
        return 0;
    }

    @Override
    public boolean save(Object saveObject) {
        return false;
    }

    @Override
    public boolean saveBatch(Object saveObjects) {
        return false;
    }

}
