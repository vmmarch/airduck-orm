package com.brouck.horizon.session;

import com.brouck.horizon.annotation.Table;
import com.brouck.horizon.exception.IllegalTableClassException;
import com.brouck.horizon.generator.wrapper.Query;

/**
 * horizon sql session封装类
 *
 * @author lts
 * Create time 2022/3/25
 */
public class HorizonSession {

    /**
     * SQLSession对象
     */
    private SqlSession _sqlSession;

    public HorizonSession(SqlSession sqlSession) {
        this._sqlSession = sqlSession;
    }

    /**
     * 创建查询对象
     */
    public <T> Query<T> createQuery(Class<T> _class) {
        if (!_class.isAnnotationPresent(Table.class)) {
            throw new IllegalTableClassException("创建查询失败，实体类必须存在@Table注解。");
        }

        return new Query<>(_sqlSession, _class);
    }

}
