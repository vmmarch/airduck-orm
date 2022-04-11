package com.airduck.generator.id;

import com.airduck.annotation.IdGenerator;
import com.airduck.session.airducksession.AirduckSession;

import java.io.Serializable;

/**
 * 自增Id生成器
 *
 * @author brouck
 * Create time 2022/3/24
 */
public class IdGeneratorForIncrement implements IdGenerator {

    @Override
    public Serializable generateId(AirduckSession rainbowSession) {
        return null;
    }

}
