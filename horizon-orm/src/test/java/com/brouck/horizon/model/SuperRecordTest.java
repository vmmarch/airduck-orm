package com.brouck.horizon.model;

import com.brouck.horizon.User;
import com.brouck.horizon.session.GetSQLSession;
import com.brouck.horizon.session.HorizonSession;
import com.brouck.horizon.session.Records;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author lts
 * Create time 2022/3/31
 */
public class SuperRecordTest {

    HorizonSession horizonSession = new HorizonSession(GetSQLSession.getSqlSession());

    @Test
    public void saveModel() {
        // 生成表
        horizonSession.addTableMetaData(User.class);
        horizonSession.executeGenerateTable();

        Records<User> users = horizonSession.createRecords(User.class, 2);
        users.get(0).setUsername("brouck-0");
        users.get(0).setNickname("unreal-brouck-0");
        users.get(0).setComment("test");
        users.get(0).setCreateTime(new Date());
        users.get(0).setUpdateTime(new Date());

        users.get(1).setUsername("brouck-1");
        users.get(1).setNickname("unreal-brouck-1");
        users.get(1).setComment("test");
        users.get(1).setCreateTime(new Date());
        users.get(1).setUpdateTime(new Date());

        horizonSession.store(users);
    }

}
