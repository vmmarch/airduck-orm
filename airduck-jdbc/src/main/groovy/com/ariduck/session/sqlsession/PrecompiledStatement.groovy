package com.ariduck.session.sqlsession

import com.airduck.exception.ConnectionOpenedException
import com.airduck.session.sqlsession.AbstractSqlSession
import com.airduck.tools.ActionUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.sql.Connection
import java.sql.PreparedStatement

/**
 * 预编译SQL声明
 *
 * @author airduck-vincent
 * Create time 2022/3/23
 */
class PrecompiledStatement {

    private static final Logger log = LoggerFactory.getLogger(AbstractSqlSession.class);

    /**
     * 预编译sql声明
     */
    private PreparedStatement preparedStatement

    /**
     * 创建预编译sql对象
     *
     * @param sql           预编译sql
     * @param connection    数据库链接
     */
    PrecompiledStatement(String sql, Connection connection) {
        if (connection == null)
            throw new ConnectionOpenedException("未获取到数据库链接（Connection）请检查是否执行了openSqlSession()以及closeSqlSession()函数")

        if (log.isDebugEnabled())
            log.debug("执行SQL: {}", sql)

        preparedStatement = connection.prepareStatement(sql)
    }

    /**
     * 创建预编译sql对象，并默认添加sql执行参数
     *
     * @param sql           预编译sql
     * @param argv          参数列表
     * @param connection    数据库链接
     */
    PrecompiledStatement(String sql, Object[] argv, Connection connection) {
        this(sql, connection)
        setArgv(argv)
    }

    /**
     * 关闭SQL声明
     */
    void close() {
        ActionUtils::close(preparedStatement)
    }

    /**
     * 设置SQL中的所有参数
     *
     * @param args 参数列表
     */
    void setArgv(Object[] args) {
        for (int i = 1; i <= args.length; i++)
            preparedStatement.setObject(i, args[i - 1])
    }

    /**
     * 设置批处理参数
     *
     * @param argv 参数列表
     */
    void setBatchArgv(List<Object[]> argv) {
        argv.each { args ->
            setArgv(args)
            preparedStatement.addBatch()
        }
    }

    /**
     * 执行查询函数
     *
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

    /**
     * 执行更新函数
     *
     * @return 更新条数
     */
    int executeUpdate() { preparedStatement.executeUpdate() }

    /**
     * 执行批处理函数
     *
     * @return 更新条数
     */
    int[] executeBatch() { preparedStatement.executeBatch() }

    boolean execute() { preparedStatement.execute() }
}
