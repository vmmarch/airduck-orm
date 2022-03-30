package com.brouck.horizon.generator.id;

import com.brouck.horizon.annotation.IdGenerator;
import com.brouck.horizon.session.HorizonSession;

import java.io.Serializable;

/**
 * 使用雪花算法生成Id
 * 
 * @author brouck
 * Create time 2022/3/24
 */
public class IdGeneratorForSnowflake implements IdGenerator {

    @Override
    public Serializable generateId(HorizonSession horizonSession) {
        return null;
    }

}
