package com.brouck.horizon.model;

import com.brouck.horizon.User;
import com.brouck.horizon.session.GetSQLSession;
import com.brouck.horizon.session.HorizonSession;
import org.junit.Test;

import java.util.Date;

/**
 * @author lts
 * Create time 2022/3/31
 */
public class SuperRecordTest {

    HorizonSession horizonSession = new HorizonSession(GetSQLSession.getSqlSession());

    @Test
    public void saveModel() {
        User user = horizonSession.createRecord(User.class);
        user.setUsername("brouck");
        user.setNickname("unreal-brouck");
        user.setComment("test");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.store(); // 将数据保存到数据库
    }

}
