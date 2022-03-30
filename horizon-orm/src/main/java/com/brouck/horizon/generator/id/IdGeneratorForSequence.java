package com.brouck.horizon.generator.id;

import com.brouck.horizon.annotation.IdGenerator;

import java.io.Serializable;

/**
 * 根据数据库序列来生成Id
 *
 * @author brouck
 * Create time 2022/3/24
 */
public class IdGeneratorForSequence implements IdGenerator {

    @Override
    public Serializable generateId() {
        return null;
    }

}
