package com.airduck.generator.id;

import com.airduck.annotation.IdGenerator;
import com.airduck.session.airducksession.AirduckSession;

import java.io.Serializable;

/**
 * 使用雪花算法生成Id
 * 
 * @author brouck
 * Create time 2022/3/24
 */
public class IdGeneratorForSnowflake implements IdGenerator {

    @Override
    public Serializable generateId(AirduckSession rainbowSession) {
        return null;
    }

}
