package com.brouck.horizon.session.sql

import com.brouck.horizon.commons.Lists
import com.brouck.horizon.generator.id.IdGeneratorForIncrement
import com.brouck.horizon.session.metadata.ColumnMetaData
import com.brouck.horizon.session.metadata.TableMetaData
import com.brouck.horizon.tools.HorizonUtils

import java.util.stream.Collectors

/**
 * sql生成器
 *
 * @author brouck
 * Create time 2022/4/1
 */
class SQLGenerator {

    /**
     * 生成插入单条数据的SQLScript
     */
    static SQLScript insert(Object object, Map<String, TableMetaData> tableMetaDataMap) {
        var tmd = tableMetaDataMap.get(HorizonUtils.getTableName(object))
        var sqlScript = new SQLScript()

        // 遍历字段获取需要插入的值
        var columns = Lists.newLinkedList()
        var params = Lists.newLinkedList()
        tmd.columns.values().forEach(column -> {
            if (column.isPrimaryKey() && column.generatedValue == IdGeneratorForIncrement) {
                return
            }

            var value = column.getValue(object)
            if (value != null) {
                columns << "`${column.name}`"
                params << value
            }

            sqlScript.addColumn(column)
        })

        sqlScript.sql = """
            insert into `${tmd.name}` (${columns.stream().collect(Collectors.joining(","))})
            values (${columns.stream().map(column -> "?").collect(Collectors.joining(","))})
        """

        sqlScript.params = params.toArray()

        return sqlScript
    }

    /**
     * 生成插入多条数据的SQLScript
     */
    static <E> SQLScript insert(Collection<E> collection, Map<String, TableMetaData> tableMetaDataMap) {
        var sqlScript = insert(collection.iterator().next(), tableMetaDataMap)
        sqlScript.batchParams = Lists.newLinkedList()

        for (E object : collection) {
            var params = Lists.newLinkedList()
            sqlScript.columns.forEach {
                var value = it.getValue(object)
                params.add(value)
            }
            sqlScript.batchParams.add(params.toArray())
        }

        return sqlScript
    }

}
