package com.airduck.dev;

import com.airduck.User;
import com.airduck.commons.ReflectionUtils;
import com.airduck.session.airducksession.properties.ColumnProperties;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * 创建字段属性测试
 *
 * @author airduck-vincent
 * Create time 2022/4/11
 */
public class CreateColumnPropertiesTest {

    @Test
    public void createColumnProperties() {
        var col_id = ColumnProperties.create(ReflectionUtils.searchField(User.class, "id"),
                false);
        System.out.println("id: " + JSON.toJSONString(col_id));

        var col_username = ColumnProperties.create(ReflectionUtils.searchField(User.class, "username"),
                false);
        System.out.println("username: " + JSON.toJSONString(col_username));

        var col_phone = ColumnProperties.create(ReflectionUtils.searchField(User.class, "email"),
                false);
        System.out.println("email: " + JSON.toJSONString(col_phone));

    }

}
