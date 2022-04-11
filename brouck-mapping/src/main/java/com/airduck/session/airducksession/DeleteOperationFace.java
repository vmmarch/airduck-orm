package com.airduck.session.airducksession;

import java.util.List;

/**
 * @author lts
 * Create time 2022/4/7
 */
public interface DeleteOperationFace {

    /**
     * 删除单个对象
     *
     * @param object 待删除的对象
     */
    <T> boolean delete(T object);

    /**
     * 删除多个对象
     *
     * @param objects 待删除的对象列表
     */
    <T> boolean deleteBatch(List<T> objects);

}
