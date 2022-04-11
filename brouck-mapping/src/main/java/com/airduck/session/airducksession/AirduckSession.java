package com.airduck.session.airducksession;

import java.util.List;

/**
 * SqlSession封装类，提供非常多便利快捷的数据库支持函数。
 *
 * @author lts
 * Create time 2022/4/7
 */
public interface AirduckSession extends SaveOperationFace, UpdateOperationFace, DeleteOperationFace {

    /**
     * 单条数据查询
     *
     * @param sql    查询语句
     * @param _class 返回类型
     * @param params 查询参数
     */
    <T> T objectQuery(String sql, Class<T> _class, Object... params);

    /**
     * 多条数据查询
     *
     * @param sql    查询语句
     * @param _class 返回类型
     * @param params 查询参数
     */
    <T> List<T> listQuery(String sql, Class<T> _class, Object... params);

    /**
     * 执行sql语句
     *
     * @param sql    sql语句
     * @param params 参数
     */
    boolean execute(String sql, Object... params);

}
