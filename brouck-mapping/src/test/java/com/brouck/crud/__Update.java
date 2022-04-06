package com.brouck.crud;

import com.brouck.User;
import com.brouck.session.BrouckSession;
import com.brouck.session.GetSQLSession;
import com.brouck.session.wrapper.Query;
import com.brouck.session.wrapper.Update;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author lts
 * Create time 2022/4/1
 */
public class __Update {

    BrouckSession brouckSession = new BrouckSession(GetSQLSession.getSqlSession());

    /**
     * 更新一条数据
     */
    @Test
    public void updateSingle() {
        // 生成表
        brouckSession.addTableMetaData(User.class);
        brouckSession.executeGenerateTable();

        Query query = brouckSession.createQuery(User.class);
        query.eq("id", 309);
        User user = query.objectQuery();
        user.setUsername(null);
        user.setCreateTime(new Date());

        // 更新User对象
        Update<User> update = brouckSession.createUpdate(User.class);
        update.eq("id", user.getId());
        update.execute(user);

    }

    /**
     * 批量更新
     */
    @Test
    public void updateBatch() {
        // 生成表
        brouckSession.addTableMetaData(User.class);
        brouckSession.executeGenerateTable();

        Query query = brouckSession.createQuery(User.class);
        query.in("id", 309, 310, 311, 312, 313);
        List<User> users = query.listQuery();

        users.get(0).setUsername("你是1");
        users.get(0).setNickname("NickName - 1");

        users.get(1).setUsername("你是2");
        users.get(1).setNickname("NickName - 2");

        users.get(2).setUsername("你是3");
        users.get(2).setNickname("NickName - 3");

        users.get(3).setUsername("你是4");
        users.get(3).setNickname("NickName - 4");

        users.get(4).setUsername("你是5");
        users.get(4).setNickname("NickName - 5");

        Update<User> update = brouckSession.createUpdate(User.class);
        update.eq("id", ":self");
        update.eq("username", ":self");
        update.execute(users);

    }

}
