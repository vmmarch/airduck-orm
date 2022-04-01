package com.brouck.generator.id;

import com.brouck.annotation.IdGenerator;
import com.brouck.session.BrouckSession;

import java.io.Serializable;

/**
 * 使用雪花算法生成Id
 * 
 * @author brouck
 * Create time 2022/3/24
 */
public class IdGeneratorForSnowflake implements IdGenerator {

    @Override
    public Serializable generateId(BrouckSession brouckSession) {
        return null;
    }

}
