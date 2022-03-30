package com.brouck.horizon.query;

import com.alibaba.fastjson.JSON;
import com.brouck.horizon.session.Query;
import com.brouck.horizon.session.GetSQLSession;
import com.brouck.horizon.session.HorizonSession;
import org.junit.Test;

/**
 * @author brouck
 * Create time 2022/3/25
 */
public class QueryTest {

    /**
     * 测试单个对象查询
     */
    @Test
    public void objectQuery() {
        HorizonSession horizonSession = new HorizonSession(GetSQLSession.getSqlSession());
        horizonSession.addTableMetaData(User.class);

        // 创建查询对象
        Query<User> query = horizonSession.createQuery(User.class);
        query.eq("username", "张三");

        System.out.println(JSON.toJSONString(query.objectQuery()));

    }

    /**
     * 测试多个对象查询
     */
    @Test
    public void listQuery() {
        HorizonSession horizonSession = new HorizonSession(GetSQLSession.getSqlSession());
        horizonSession.addTableMetaData(User.class);

        // 创建查询对象
        Query<User> query = horizonSession.createQuery(User.class);
        query.in("id", 0, 1, 2, 3);

        System.out.println(JSON.toJSONString(query.listQuery()));
    }

}
