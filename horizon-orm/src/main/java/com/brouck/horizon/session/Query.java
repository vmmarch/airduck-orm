package com.brouck.horizon.session;

import com.brouck.horizon.generator.wrapper.ConditionBuilder;
import com.brouck.horizon.session.metadata.TableMetaData;
import com.brouck.horizon.tools.StringUtils;

import java.util.List;

/**
 * @author lts
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
            session.objectQuery(buildSQL(), tableMetaData.getEntityClass(), getParameters().toArray()), false);
    }

    /**
     * 查询多个对象
     */
    @SuppressWarnings("unchecked")
    public List<Entity> listQuery() {
        return (List<Entity>) sqlSession.openTransaction(session -> sqlSession.listQuery(buildSQL(),
                tableMetaData.getEntityClass(), getParameters().toArray()), false);
    }

    /**
     * @return 构建查询sql
     */
    private String buildSQL() {
        // 构建查询sql
        return StringUtils.format("select {} from {} {}", tableMetaData.getAllQueryColumns(),
                tableMetaData.getTableName(),
                getWhereSQL());
    }

}