package com.airduck.annotation;

import com.airduck.session.airducksession.AirduckSession;

import java.io.Serializable;

/**
 * 自定义Id生成器
 *
 * @author airduck
 * Create time 2022/3/24
 */
public interface IdGenerator {

    /**
     * @return 返回生成的Id
     */
    Serializable generateId(AirduckSession airduckSession);

}
