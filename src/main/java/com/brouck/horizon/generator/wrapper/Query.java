package com.brouck.horizon.generator.wrapper;

import com.brouck.horizon.annotation.Table;
import com.brouck.horizon.session.SqlSession;
import com.brouck.horizon.tools.StringUtils;

import java.util.List;

/**
 * @author lts
 * Create time 2022/3/25
 */
public class Query<Entity> extends ConditionBuilder<Query<Entity>>
        implements Wrapper {

    private final SqlSession sqlSession;

    private final Class<Entity> _class;

    public Query(SqlSession sqlSession, Class<Entity> aClass) {
        this.sqlSession = sqlSession;
        this._class = aClass;
    }

    /**
     * 查询单个对象
     */
    public Entity single() {
        sqlSession.openSqlSession(false);
        Entity entity = sqlSession.executeQuery(buildSQL(), _class, getParameters().toArray());
        sqlSession.closeSqlSession();

        return entity;
    }

    /**
     * 查询多个对象
     */
    public List<Entity> list() {
        sqlSession.openSqlSession(false);
        List<Entity> entities = sqlSession.executeQueryArray(buildSQL(), _class, getParameters().toArray());
        sqlSession.closeSqlSession();

        return entities;
    }

    private String buildSQL() {
        // 构建查询sql
        Table tableAnno = _class.getAnnotation(Table.class);
        String sql = StringUtils.format("select * from {} {}",
                tableAnno.name(),
                getWhereSQL());

        return sql;
    }

}