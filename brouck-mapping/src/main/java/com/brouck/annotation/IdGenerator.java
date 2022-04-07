package com.brouck.annotation;

import com.brouck.session.wrapsession.RainbowSession;

import java.io.Serializable;

/**
 * 自定义Id生成器
 *
 * @author brouck
 * Create time 2022/3/24
 */
public interface IdGenerator {

    /**
     * @return 返回生成的Id
     */
    Serializable generateId(RainbowSession rainbowSession);

}
