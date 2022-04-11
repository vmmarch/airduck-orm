package com.airduck.generator.id;

import com.airduck.annotation.IdGenerator;
import com.airduck.session.airducksession.AirduckSession;

import java.io.Serializable;

/**
 * 根据数据库序列来生成Id
 *
 * @author airduck
 * Create time 2022/3/24
 */
public class IdGeneratorForSequence implements IdGenerator {

    @Override
    public Serializable generateId(AirduckSession airduckSession) {
        return null;
    }

}
