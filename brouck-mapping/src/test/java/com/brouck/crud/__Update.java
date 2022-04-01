package com.brouck.crud;

import com.brouck.User;
import com.brouck.session.BrouckSession;
import com.brouck.session.GetSQLSession;
import com.brouck.session.wrapper.Query;
import com.brouck.session.wrapper.Update;
import org.junit.Test;

import java.util.Date;

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

        Query<User> query = brouckSession.createQuery(User.class);
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
    public void updateBatch() {
        // 生成表
        brouckSession.addTableMetaData(User.class);
        brouckSession.executeGenerateTable();
    }

}
