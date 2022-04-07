package com.brouck.session.wrapsession;

import com.brouck.session.sqlsession.SqlSession;

import java.util.List;

/**
 * @author lts
 * Create time 2022/4/7
 */
public class DefaultRainbowSession implements RainbowSession {

    /**
     * sqlSession, 用于操作JDBC
     */
    private final SqlSession _session;

    /**
     * 初始化RainbowSession
     */
    public DefaultRainbowSession(SqlSession sqlSession) {
        this._session = sqlSession;
    }

    @Override
    public <T> boolean delete(T object) {
        return false;
    }

    @Override
    public <T> boolean deleteBatch(List<T> objects) {
        return false;
    }

    @Override
    public <T> T objectQuery(String sql, Class<T> _class, Object... params) {
        return null;
    }

    @Override
    public <T> List<T> listQuery(String sql, Class<T> _class, Object... params) {
        return null;
    }

    @Override
    public boolean execute(String sql, Object... params) {
        return false;
    }

    @Override
    public <T> boolean save(T object) {
        return false;
    }

    @Override
    public <T> boolean saveBatch(List<T> objects) {
        return false;
    }

    @Override
    public <T> boolean saveOrUpdate(T object) {
        return false;
    }

    @Override
    public <T> boolean saveOrUpdateBatch(List<T> objects) {
        return false;
    }

    @Override
    public <T> boolean update(T object) {
        return false;
    }

    @Override
    public <T> boolean updateBatch(List<T> objects) {
        return false;
    }

}
