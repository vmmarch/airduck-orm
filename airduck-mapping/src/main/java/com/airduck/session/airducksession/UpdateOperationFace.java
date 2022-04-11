package com.airduck.session.airducksession;

import java.util.List;

/**
 * @author lts
 * Create time 2022/4/7
 */
public interface UpdateOperationFace {

    /**
     * 更新单个对象
     *
     * @param object 待更新的对象
     */
    <T> boolean update(T object);

    /**
     * 更新多个对象
     *
     * @param objects 待更新的对象列表
     */
    <T> boolean updateBatch(List<T> objects);

}
