package com.brouck.horizon;

import java.sql.Connection;
import java.util.List;

/**
 * @author brouck
 * Create time 2022/3/22
 */
public interface SqlSession
{

    /**
     * 设置数据库链接
     */
    void setConnectionContext(Connection connection);

    /**
     * 打开SqlSession操作，这个操作会开启数据库链接、事务这些工作。
     */
    void openSqlSession();

    /**
     * 关闭SqlSession操作，这个操作会关闭数据库链接、事务这些工作。
     */
    void closeSqlSession();

    /**
     * 查询单个对象
     *
     * @param sql       sql语句
     * @param _class    类对象
     * @param args      查询参数
     */
    <T> T queryForObject(String sql, Class<T> _class, Object... args);

    /**
     * 查询集合列表
     *
     * @param sql       sql语句
     * @param _class    类对象
     * @param args      查询参数
     */
    <T> List<T> queryForList(String sql, Class<T> _class, Object... args);

    /**
     * 更新对象
     *
     * @param updateObject  更新的对象数据
     * @param whereSql      where条件sql
     * @return              成功更新条数，大于1表示更新成功
     */
    int update(Object updateObject, String whereSql);

    /**
     * 批量更新对象
     *
     * @param updateObjects 更新的对象数据
     * @param whereSql      where条件sql
     * @return              成功更新条数，大于1表示更新成功（一条失败全部失败）
     */
    int updateBatch(List<Object> updateObjects, String whereSql);

    /**
     * 根据主键更新对象
     *
     * @param updateObject  更新的对象数据
     * @return              成功更新条数，大于1表示更新成功
     */
    int updateByPrimaryKey(Object updateObject);

    /**
     * 根据主键批量更新对象
     *
     * @param updateObjects 更新的对象数据
     * @return              成功更新条数，大于1表示更新成功（一条失败全部失败）
     */
    int updateBatchByPrimaryKey(List<Object> updateObjects);

    /**
     * 保存对象到数据库
     *
     * @param saveObject    保存的对象
     * @return              是否保存成功
     */
    boolean save(Object saveObject);

    /**
     * 批量保存对象到数据库
     *
     * @param saveObjects   保存的对象列表
     * @return              是否保存成功（一条失败全部失败）
     */
    boolean saveBatch(Object saveObjects);

}
