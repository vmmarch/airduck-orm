package com.brouck.model;

import com.brouck.commons.Lists;
import com.brouck.User;
import com.brouck.session.GetSQLSession;
import com.brouck.session.BrouckSession;
import org.junit.Test;

import java.util.Date;

/**
 * @author brouck
 * Create time 2022/3/31
 */
public class SuperRecordTest {

    BrouckSession brouckSession = new BrouckSession(GetSQLSession.getSqlSession());

    @Test
    public void storeSingle() {
        // 生成表
        brouckSession.addTableMetaData(User.class);
        brouckSession.executeGenerateTable();

        User user = brouckSession.createRecord(User.class);
        user.setUsername("brouck-0");
        user.setNickname("unreal-brouck-0");
        user.setComment("test");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        // 保存到数据库
        user.store();
    }


    @Test
    public void sotreList() {
        // 生成表
        brouckSession.addTableMetaData(User.class);
        brouckSession.executeGenerateTable();

        var users = Lists.newArrayList();
        for (int i = 0; i < 10000; i++) {
            User user = new User();
            user.setUsername("brouck-" + i);
            user.setNickname("unreal-brouck-" + i);
            user.setComment(String.valueOf(i));
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            users.add(user);
        }

        // 计算以下代码耗时
        long start = System.currentTimeMillis();
        brouckSession.store(users); // 保存一万条数据到数据库
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));

    }

}
