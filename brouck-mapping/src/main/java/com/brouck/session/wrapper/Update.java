package com.brouck.session.wrapper;

import com.brouck.commons.Lists;
import com.brouck.commons.Reflections;
import com.brouck.generator.wrapper.ConditionBuilder;
import com.brouck.session.SqlSession;
import com.brouck.session.metadata.ColumnMetaData;
import com.brouck.session.metadata.TableMetaData;

import java.util.List;

/**
 * @author lts
 * Create time 2022/4/1
 */
public class Update<Entity> extends ConditionBuilder<Update<Entity>> {

    /**
     * sqlSession
     */
    private final SqlSession sqlSession;

    /**
     * 表元数据
     */
    private final TableMetaData tableMetaData;

    /**
     * 构造函数
     */
    public Update(SqlSession sqlSession, TableMetaData tableMetaData, Class<Entity> entityClass) {
        this.sqlSession = sqlSession;
        this.tableMetaData = tableMetaData;
        /**
         * 实体类
         */
    }

    /**
     * 执行更新一条记录
     */
    public boolean execute(Object object) {
        var sql = new StringBuilder("update ").append("`")
                .append(tableMetaData.getName()).append("`").append(" set ");

        // 获取所有字段
        var params = Lists.newLinkedList();
        tableMetaData.getColumns().values().forEach(column -> {
            if (column.isPrimaryKey())
                return;

            var value = Reflections.getValue(column.getColumnField(), object);
            params.add(value);
            sql.append("`").append(column.getName()).append("`").append(" = ?,");
        });
        sql.deleteCharAt(sql.length() - 1).append(getWhereSQL());
        // 添加where参数
        params.addAll(getParameters());

        return sqlSession.openTransaction(session ->
                session.executeUpdate(sql.toString(), params.toArray()),
                false) > 1;
    }

    /**
     * 执行批量更新（批量更新只会根据主键去更新数据）
     */
    public boolean execute(List<Entity> objects) {
        var sql = new StringBuilder("update ").append("`")
                .append(tableMetaData.getName()).append("`").append(" set ");

        List<Object[]> batchParams = Lists.newLinkedList();

        // 获取所有字段
        tableMetaData.getColumns().values().forEach(column -> {
            if (column.isPrimaryKey())
                return;
            sql.append("`").append(column.getName()).append("`").append(" = ?,");
        });
        sql.deleteCharAt(sql.length() - 1).append(getWhereSQL());

        // 获取所有SQL参数
        objects.forEach(object -> {
            // 单条记录的参数列表
            Object primaryKeyValue = null;
            List<Object> objectsParam = Lists.newLinkedList();

            for (ColumnMetaData columnMetaData : tableMetaData.getColumns().values()) {
                var value = Reflections.getValue(columnMetaData.getColumnField(), object);

                if (columnMetaData.isPrimaryKey()) {
                    primaryKeyValue = value;
                    continue;
                }

                objectsParam.add(value);
            }

            // 添加单条记录的参数列表
            objectsParam.add(primaryKeyValue);
            batchParams.add(objectsParam.toArray());
        });

        return sqlSession.openTransaction(session ->
                        session.executeUpdateBatch(sql.toString(), batchParams),
                false).length > 0;
    }

}
