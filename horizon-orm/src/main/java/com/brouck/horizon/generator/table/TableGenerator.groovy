package com.brouck.horizon.generator.table

import com.brouck.horizon.commons.StringUtils
import com.brouck.horizon.session.HorizonSession
import com.brouck.horizon.session.metadata.TableMetaData

/**
 * 表生成器
 *
 * @author brouck
 * Create time 2022/3/29
 */
class TableGenerator {

    /**
     * 生成一张表
     *
     * @param horizonSession sqlSession
     * @param metaDataTables 所有表的数据
     */
    static def generate(HorizonSession sqlSession, List<TableMetaData> metaDataTables) {
        var metaDataQuery = sqlSession.getMetaDataQuery()

        // 待更新的数据库表
        var tobeUpdated = []
        // 待新增的数据库表
        var tobeSaved = []

        // 查询当前数据库所有表
        var tables = metaDataQuery.tables()

        metaDataTables.forEach(metadata -> {
            // 如果当前表存在数据库就添加到待更新表中
            // 如果不存在就添加到待新增中
            if (metadata.tableName in tables) {
                tobeUpdated << metadata
            } else {
                tobeSaved << metadata
            }
        })

        doSave(sqlSession, tobeSaved)
        doUpdate(sqlSession, tobeUpdated)

    }

    /**
     * 执行新增表操作
     */
    static def doSave(HorizonSession sqlSession, List<TableMetaData> metaDataTables) {
        metaDataTables.forEach(table -> {
            // 构建创建表的SQL语句
            var createTableSQL = new StringBuilder("create table `${table.tableName}` (\n")
            var primaryKeys = new StringBuilder()

            table.columns.values().forEach(column -> {
                // 解析类型
                var type = "${column.type != "timestamp" ? "${column.type}(${column.length})" : "${column.type}"}"
                // 是否可以为空
                var nullable = "${column.nullable ? "" : "not null"}"
                // 是否有备注
                var comment = "${StringUtils.isEmpty(column.comment) ? "" : "comment '${column.comment}'"}"

                createTableSQL.append("`${column.name}` ${type} ${nullable} ${comment},\n")

                // 主键判断
                if (column.primaryKey) {
                    primaryKeys.append "`${column.name}`,"
                }
            })
            primaryKeys.deleteCharAt(primaryKeys.length() - 1)
            createTableSQL.append("""
                primary key ($primaryKeys) using btree
                );
            """)
            println(createTableSQL)
            // 执行创建表的sql
            sqlSession.execute(createTableSQL.toString())
        })
    }

    /**
     * 执行更新表操作
     */
    static def doUpdate(HorizonSession sqlSession, List<TableMetaData> metaDataTables) {
        metaDataTables.forEach(table -> {
        })
    }

}
