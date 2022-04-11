package com.airduck.session.sqlsession

import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.brouck.exception.MultipleResultSetsException

import java.sql.ResultSet

/**
 * @author brouck
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
            columns << metadata.getColumnLabel(i)

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
    public <T> T asObject(Class<T> _class) {
        if (resultData.size() > 1)
            throw new MultipleResultSetsException("查询到了多个结果集，一共查询到了${resultData.size()}条数据。但预期结果只有一条。")

        if (resultData.isEmpty())
            return null

        return new JSONObject(resultData[0]).toJavaObject(_class) as T
    }

    /**
     * 将结果集转换成集合对象
     *
     * @param _class 被转换的对象
     */
    public <T> List<T> asList(Class<T> _class) {
        if (_class == String) {
            def retstr = []
            resultData.forEach({
                it.entrySet().forEach({
                    retstr << it.value
                })
            })

            return retstr
        }

        return new JSONArray(resultData).toJavaList(_class) as List<T>
    }

}