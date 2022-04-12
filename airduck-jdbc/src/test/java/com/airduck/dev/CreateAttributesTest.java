package com.airduck.dev;

import com.airduck.User;
import com.airduck.commons.ReflectionUtils;
import com.airduck.session.airducksession.properties.ColumnAttributes;
import com.airduck.session.airducksession.properties.EntityAttributes;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * 创建字段属性测试
 *
 * @author airduck-vincent
 * Create time 2022/4/11
 */
public class CreateAttributesTest {

    @Test
    public void createColumnAttributes() {
        var col_id = ColumnAttributes.create("user", false,
                ReflectionUtils.searchField(User.class, "id"));
        System.out.println("id: " + JSON.toJSONString(col_id));

        var col_username = ColumnAttributes.create("user", false,
                ReflectionUtils.searchField(User.class, "username"));
        System.out.println("username: " + JSON.toJSONString(col_username));

    }


    @Test
    public void createEntityAttributes() {
        System.out.println(JSON.toJSONString(new EntityAttributes(User.class)));
    }

}
