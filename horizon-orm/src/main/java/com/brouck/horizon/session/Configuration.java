package com.brouck.horizon.session;

import com.brouck.horizon.tools.ActionUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author brouck
 * Create time 2022/3/22
 */
public class Configuration {

    /**
     * 数据源
     */
    private final DataSource dataSource;

    /**
     * 是否关闭自动提交
     */
    private boolean closeAutoCommit;

    public Configuration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 打开数据库链接
     */
    public Connection openConnection(boolean openTransaction) throws SQLException {
        var connection = dataSource.getConnection();
        connection.setAutoCommit(!openTransaction);
        closeAutoCommit = !connection.getAutoCommit();

        return connection;
    }

    /**
     * 关闭数据库链接
     */
    public void closeConnection(Connection connection, Throwable throwable) {
        try {
            // 提交链接
            if (closeAutoCommit) {
                if (throwable != null) {
                    ActionUtils.connectionRollback(connection);
                } else {
                    ActionUtils.commit(connection);

                }
            }
        } finally {
            closeAutoCommit = true;
            ActionUtils.close(connection);
        }
    }

}
