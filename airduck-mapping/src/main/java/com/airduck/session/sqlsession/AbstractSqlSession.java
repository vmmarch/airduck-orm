package com.airduck.session.sqlsession;

import com.airduck.exception.ConnectionOpenedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

/**
 * @author airduck
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
        closeSqlSession(null);
    }

    @Override
    public void closeSqlSession(Throwable e) {
        configuration.closeConnection(this.currentConnection, e);
        this.currentConnection = null;
    }

    @Override
    public <T> T openTransaction(Function<SqlSession, T> function, boolean open) {
        T ret = null;
        Throwable throwable = null;
        try {
            openSqlSession(open);
            ret = function.apply(this);
        } catch (Exception e) {
            e.printStackTrace();
            throwable = e;
        } finally {
            closeSqlSession(throwable);
        }
        return ret;
    }

    /** 校验SqlSession是否重复开启 */
    private void checkConnectionOpened() {
        if (this.currentConnection != null)
            throw new ConnectionOpenedException("在当前SqlSession上下文中已经打开了一个SqlSession，请等待上一个SqlSession的关闭。");
    }

    @Override
    public <T> T objectQuery(String sql, Class<T> _class, Object... args) {
        PrecompiledStatement statement = new PrecompiledStatement(sql, args, this.currentConnection);
        ExecuteQuerySet executeQuerySet = statement.executeQuery();
        statement.close();

        return executeQuerySet.asObject(_class);
    }

    @Override
    public <T> List<T> listQuery(String sql, Class<T> _class, Object... args) {
        PrecompiledStatement statement = new PrecompiledStatement(sql, args, this.currentConnection);
        ExecuteQuerySet executeQuerySet = statement.executeQuery();
        statement.close();

        return executeQuerySet.asList(_class);
    }

    @Override
    public int executeUpdate(String sql, Object... args) {
        PrecompiledStatement statement = new PrecompiledStatement(sql, args, this.currentConnection);
        int ret = statement.executeUpdate();
        statement.close();

        return ret;
    }

    @Override
    public int[] executeUpdateBatch(String sql, List<Object[]> args) {
        PrecompiledStatement statement = new PrecompiledStatement(sql, this.currentConnection);
        statement.setBatchArgv(args);
        int[] ret = statement.executeBatch();
        statement.close();

        return ret;
    }

    @Override
    public boolean execute(String sql) {
        PrecompiledStatement statement = new PrecompiledStatement(sql, this.currentConnection);
        boolean ret = statement.execute();
        statement.close();

        return ret;
    }
}
