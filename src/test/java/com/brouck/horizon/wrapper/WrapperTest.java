package com.brouck.horizon.wrapper;

import com.alibaba.fastjson.JSON;
import com.brouck.horizon.generator.wrapper.Query;
import com.brouck.horizon.session.GetSQLSession;
import com.brouck.horizon.session.HorizonSession;
import org.junit.Test;

/**
 * @author lts
 * Create time 2022/3/25
 */
public class WrapperTest {

    @Test
    public void searchWrapperTest() {
        HorizonSession horizonSession = new HorizonSession(GetSQLSession.getSqlSession());
        Query<User> query = horizonSession.createQuery(User.class);
        query.in("id", 2, 3);

        System.out.println(JSON.toJSONString(query.list()));
    }

}
