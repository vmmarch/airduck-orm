package com.brouck.session.wrapper;

import com.brouck.commons.Lists;
import com.brouck.commons.Reflections;
import com.brouck.generator.wrapper.ConditionBuilder;
import com.brouck.session.SqlSession;
import com.brouck.session.metadata.TableMetaData;

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
     * 实体类
     */
    private Class<Entity> entityClass;

    /**
     * 构造函数
     */
    public Update(SqlSession sqlSession, TableMetaData tableMetaData, Class<Entity> entityClass) {
        this.sqlSession = sqlSession;
        this.tableMetaData = tableMetaData;
        this.entityClass = entityClass;
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
            if (value == null)
                return;

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

}
