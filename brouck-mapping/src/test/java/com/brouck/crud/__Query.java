package com.brouck.crud;

import com.alibaba.fastjson.JSON;
import com.brouck.commons.DateUtils;
import com.brouck.User;
import com.brouck.session.wrapper.Query;
import com.brouck.session.GetSQLSession;
import com.brouck.session.BrouckSession;
import org.junit.Test;

/**
 * @author brouck
 * Create time 2022/3/25
 */
public class __Query {

    /**
     * 测试单个对象查询
     */
    @Test
    public void objectQuery() {
        BrouckSession brouckSession = new BrouckSession(GetSQLSession.getSqlSession());
        brouckSession.addTableMetaData(User.class);

        // 创建查询对象
        Query<User> query = brouckSession.createQuery(User.class);
        query.eq("username", "张三丰");

        User user = query.objectQuery();
        user.setUsername("张叽叽");

        System.out.printf("createTime: %s\n", DateUtils.format(user.getCreateTime()));
        System.out.println(JSON.toJSONString(user));

    }

    /**
     * 测试多个对象查询
     */
    @Test
    public void listQuery() {
        BrouckSession brouckSession = new BrouckSession(GetSQLSession.getSqlSession());
        brouckSession.addTableMetaData(User.class);

        // 创建查询对象
        Query<User> query = brouckSession.createQuery(User.class);
        query.in("id", 0, 1, 2, 3);

        System.out.println(JSON.toJSONString(query.listQuery()));
    }

}
