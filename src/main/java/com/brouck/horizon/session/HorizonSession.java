package com.brouck.horizon.session;

import com.brouck.horizon.generator.wrapper.Wrapper;

import java.util.List;

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

    /**
     * 查询单个对象
     */
    public <Entity> Entity queryForObject(Wrapper<Entity> wrapper) {
        return null;
    }

    /**
     * 查询单个对象
     */
    public <Entity> List<Entity> queryForList(Wrapper<Entity> wrapper) {
        return null;
    }

}
