package com.brouck.session;

import com.brouck.commons.StringUtils;
import com.brouck.generator.wrapper.ConditionBuilder;
import com.brouck.session.metadata.TableMetaData;

import java.util.List;

/**
 * @author brouck
 * Create time 2022/3/25
 */
public class Query<Entity> extends ConditionBuilder<Query<Entity>> {

    private final SqlSession sqlSession;

    private final TableMetaData tableMetaData;

    /**
     * 创建查询对象
     */
    Query(SqlSession sqlSession, TableMetaData tableMetaData) {
        this.sqlSession = sqlSession;
        this.tableMetaData = tableMetaData;
    }

    /**
     * 查询单个对象
     */
    @SuppressWarnings("unchecked")
    public Entity objectQuery() {
        return (Entity) sqlSession.openTransaction(session ->
            session.objectQuery(sql(), tableMetaData.getEntityClass(), getParameters().toArray()), false);
    }

    /**
     * 查询多个对象
     */
    @SuppressWarnings("unchecked")
    public List<Entity> listQuery() {
        return (List<Entity>) sqlSession.openTransaction(session -> sqlSession.listQuery(sql(),
                tableMetaData.getEntityClass(), getParameters().toArray()), false);
    }

    /**
     * @return 构建查询sql
     */
    private String sql() {
        // 构建查询sql
        return StringUtils.format("select {} from {} {}", tableMetaData.getAllQueryColumns(),
                tableMetaData.getName(),
                getWhereSQL());
    }

}