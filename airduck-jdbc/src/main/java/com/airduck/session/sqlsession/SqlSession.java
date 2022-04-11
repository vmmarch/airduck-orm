package com.airduck.session.sqlsession;

import java.util.List;
import java.util.function.Function;

/**
 * @author airduck-vincent
 * Create time 2022/3/22
 */
public interface SqlSession {

    /**
     * 打开SqlSession操作，这个操作会开启数据库链接、事务这些工作。
     *
     * @param openTransaction 是否开启事务
     */
    void openSqlSession(boolean openTransaction);

    /**
     * 关闭SqlSession操作，这个操作会关闭数据库链接、事务这些工作。
     */
    void closeSqlSession();

    /**
     * 关闭SqlSession操作，这个操作会关闭数据库链接、事务这些工作。
     */
    void closeSqlSession(Throwable e);

    /**
     * 自动打开和关闭SqlSession
     */
    <T> T openTransaction(Function<SqlSession, T> function, boolean open);

    /**
     * 查询单个对象
     *
     * @param sql    sql语句
     * @param _class 类对象
     * @param args   查询参数
     */
    <T> T objectQuery(String sql, Class<T> _class, Object... args);

    /**
     * 查询集合列表
     *
     * @param sql    sql语句
     * @param _class 类对象
     * @param args   查询参数
     */
    <T> List<T> listQuery(String sql, Class<T> _class, Object... args);

    /**
     * 更新一条数据
     *
     * @param sql  sql语句
     * @param args 参数列表
     * @return 结果大于1表示更新成功
     */
    int executeUpdate(String sql, Object... args);

    /**
     * 批量更新
     *
     * @param sql  更新sql
     * @param args 参数列表
     * @return 结果大于1表示更新成功
     */
    int[] executeUpdateBatch(String sql, List<Object[]> args);

    /**
     * 执行ddl语句
     *
     * @param sql sql语句
     */
    boolean execute(String sql);

}
