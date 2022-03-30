package com.brouck.horizon.generator.id;

import com.brouck.horizon.annotation.IdGenerator;
import com.brouck.horizon.session.HorizonSession;

import java.io.Serializable;

/**
 * 自增Id生成器
 *
 * @author brouck
 * Create time 2022/3/24
 */
public class IdGeneratorForIncrement implements IdGenerator {

    @Override
    public Serializable generateId(HorizonSession horizonSession) {
        return null;
    }

}
