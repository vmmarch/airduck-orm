package com.brouck.horizon;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author brouck
 * Create time 2022/3/22
 */
public record Configuration(DataSource dataSource) {

    /**
     * 打开数据库链接
     */
    public Connection openConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * 关闭数据库链接
     */
    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

}
