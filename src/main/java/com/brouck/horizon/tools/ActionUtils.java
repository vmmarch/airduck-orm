package com.brouck.horizon.tools;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author lts
 * Create time 2022/3/23
 */
public class ActionUtils {

    /**
     * 关闭流或者链接
     * @param closeable 自动关闭接口
     */
    public static void close(AutoCloseable closeable) {
        try {
            if (closeable != null)
                closeable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     * @param connection 数据库链接
     */
    public static void connectionRollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
