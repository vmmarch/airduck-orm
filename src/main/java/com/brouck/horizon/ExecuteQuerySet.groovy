package com.brouck.horizon

import java.sql.ResultSet

/**
 * @author lts
 * Create time 2022/3/23
 */
class ExecuteQuerySet {

    /** 结果集数据 */
    private List<Map<String, Object>> resultData = []

    ExecuteQuerySet(ResultSet resultSet) {
        initExecuteQuerySet(resultSet)
    }

    /**
     * 初始化结果集
     *
     * @param resultSet 原生sql结果集
     */
    private void initExecuteQuerySet(ResultSet resultSet) {
        // 获取结果集元数据
        var metadata = resultSet.getMetaData()

        // 查询所有列名
        var columns = []
        var columnCount = metadata.getColumnCount()
        for (int i = 1; i <= columnCount; i++)
            columns << metadata.getColumnName(i)

        // 构建结果集
        while(resultSet.next()) {
            var data = new HashMap<String, Object>()
            for (String column : columns)
                data[column] = resultSet.getObject(column as String)
            resultData << data
        }
    }

    /**
     * 将结果集转换成对象
     *
     * @param _class 被转换的对象
     */
    public <T> T asObject(Class<?> _class) {
        return null
    }

    /**
     * 将结果集转换成集合对象
     *
     * @param _class 被转换的对象
     */
    public <T> List<T> asList(Class<?> _class) {
        return null
    }

}
