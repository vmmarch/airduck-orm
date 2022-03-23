package com.brouck.horizon

import com.brouck.nanolimit.tools.CloseUtils

import java.sql.Connection
import java.sql.PreparedStatement

/**
 * 预编译SQL声明
 *
 * @author lts
 * Create time 2022/3/23
 */
class PrecompiledStatement {

    /**
     * 预编译sql声明
     */
    private PreparedStatement preparedStatement;

    PrecompiledStatement(String sql, Object[] argv, Connection connection) {
        preparedStatement = connection.prepareStatement(sql)
        setArgv(argv)
    }

    /**
     * 关闭SQL声明
     */
    void close() {
        CloseUtils::close(preparedStatement)
    }

    /**
     * 设置SQL中的所有参数
     * @param args 参数列表
     */
    void setArgv(Object[] args) {
        for (int i = 0; i < args.length; i++)
            preparedStatement.setObject(i, args[i])
    }

    /**
     * @return 执行查询后封装结果集
     */
    ExecuteQuerySet executeQuery() {
        // 执行查询
        var resultSet = preparedStatement.executeQuery()
        // 封装结果集
        var executeQuerySet = new ExecuteQuerySet(resultSet)
        // 关闭结果集
        resultSet.close()

        return executeQuerySet
    }

}
