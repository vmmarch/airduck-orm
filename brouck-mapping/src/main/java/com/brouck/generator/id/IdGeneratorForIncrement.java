package com.brouck.generator.id;

import com.brouck.annotation.IdGenerator;
import com.brouck.session.wrapsession.RainbowSession;

import java.io.Serializable;

/**
 * 自增Id生成器
 *
 * @author brouck
 * Create time 2022/3/24
 */
public class IdGeneratorForIncrement implements IdGenerator {

    @Override
    public Serializable generateId(RainbowSession rainbowSession) {
        return null;
    }

}
