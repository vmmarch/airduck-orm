package com.airduck.session.airducksession;

import java.util.List;

/**
 * @author lts
 * Create time 2022/4/7
 */
public interface SaveOperationFace {

    /**
     * 保存单个对象
     *
     * @param object 待保存的对象
     */
    <T> boolean save(T object);

    /**
     * 保存多个对象
     *
     * @param objects 待保存的对象列表
     */
    <T> boolean saveBatch(List<T> objects);

    /**
     * 保存或更新一个对象
     *
     * @param object 待保存或更新的对象
     */
    <T> boolean saveOrUpdate(T object);

    /**
     * 保存或更新多个对象
     *
     * @param objects 待保存或更新的对象列表
     */
    <T> boolean saveOrUpdateBatch(List<T> objects);

}
