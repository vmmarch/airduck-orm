package com.brouck.horizon;

import com.brouck.horizon.exception.ConnectionOpenedException;
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
    public void openSqlSession(boolean openTransaction) {
        try {
            checkConnectionOpened();
            this.currentConnection = configuration.openConnection(openTransaction);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeSqlSession() {
        configuration.closeConnection(this.currentConnection);
        this.currentConnection = null;
    }

    /** 校验SqlSession是否重复开启 */
    private void checkConnectionOpened() {
        if (this.currentConnection != null)
            throw new ConnectionOpenedException("在当前SqlSession上下文中已经打开了一个SqlSession，请等待上一个SqlSession的关闭。");
    }

    @Override
    public <T> T queryForObject(String sql, Class<T> _class, Object... args) {
        PrecompiledStatement statement = new PrecompiledStatement(sql, args, this.currentConnection);
        ExecuteQuerySet executeQuerySet = statement.executeQuery();
        statement.close();

        return executeQuerySet.asObject(_class);
    }

    @Override
    public <T> List<T> queryForList(String sql, Class<T> _class, Object... args) {
        PrecompiledStatement statement = new PrecompiledStatement(sql, args, this.currentConnection);
        ExecuteQuerySet executeQuerySet = statement.executeQuery();
        statement.close();

        return executeQuerySet.asList(_class);
    }

    @Override
    public int update(String sql, Object... args) {
        PrecompiledStatement statement = new PrecompiledStatement(sql, args, this.currentConnection);
        int ret = statement.executeUpdate();
        statement.close();

        return ret;
    }

    @Override
    public int[] updateBatch(String sql, List<Object[]> args) {
        PrecompiledStatement statement = new PrecompiledStatement(sql, this.currentConnection);
        statement.setBatchArgv(args);
        int[] ret = statement.executeBatch();
        statement.close();

        return ret;
    }

}
