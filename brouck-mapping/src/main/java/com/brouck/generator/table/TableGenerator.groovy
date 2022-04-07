package com.brouck.generator.table

import com.brouck.commons.StringUtils
import com.brouck.generator.id.IdGeneratorForIncrement
import com.brouck.session.BrouckSession
import com.brouck.session.metadata.TableMetaData

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
     * @param brouckSession sqlSession
     * @param metaDataTables 所有表的数据
     */
    static def generate(BrouckSession sqlSession, List<TableMetaData> metaDataTables) {
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
            if (metadata.name in tables) {
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
    static def doSave(BrouckSession sqlSession, List<TableMetaData> metaDataTables) {
        metaDataTables.forEach(table -> {
            // 构建创建表的SQL语句
            var createTableSQL = new StringBuilder("create table `${table.name}` (\n")
            var primaryKeys = new StringBuilder()

            table.columns.values().forEach(column -> {
                // 解析类型
                var type = "${column.type != "datetime" ? "${column.type}(${column.length})" : "${column.type}"}"
                // 是否可以为空
                var nullable = "${column.nullable ? "" : "not null"}"
                // 是否有备注
                var comment = "${StringUtils.isEmpty(column.comment) ? "" : "comment '${column.comment}'"}"
                // 如果当前字段是主键并且生成器是主键自增
                var autoIncrement = ""
                if (column.primaryKey && column.generatedValue == IdGeneratorForIncrement) {
                    autoIncrement = "auto_increment"
                }

                createTableSQL.append("`${column.name}` ${type} ${nullable} ${comment} ${autoIncrement},\n")

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
    static def doUpdate(BrouckSession sqlSession, List<TableMetaData> metaDataTables) {
        metaDataTables.forEach(table -> {
        })
    }

}