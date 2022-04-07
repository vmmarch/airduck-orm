package com.brouck.generator.id;

import com.brouck.annotation.IdGenerator;
import com.brouck.session.wrapsession.RainbowSession;

import java.io.Serializable;

/**
 * 根据数据库序列来生成Id
 *
 * @author brouck
 * Create time 2022/3/24
 */
public class IdGeneratorForSequence implements IdGenerator {

    @Override
    public Serializable generateId(RainbowSession rainbowSession) {
        return null;
    }

}
