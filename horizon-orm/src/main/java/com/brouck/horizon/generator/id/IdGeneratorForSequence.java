package com.brouck.horizon.generator.id;

import com.brouck.horizon.annotation.IdGenerator;
import com.brouck.horizon.session.HorizonSession;

import java.io.Serializable;

/**
 * 根据数据库序列来生成Id
 *
 * @author brouck
 * Create time 2022/3/24
 */
public class IdGeneratorForSequence implements IdGenerator {

    @Override
    public Serializable generateId(HorizonSession horizonSession) {
        return null;
    }

}
