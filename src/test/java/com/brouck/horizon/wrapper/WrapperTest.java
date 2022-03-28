package com.brouck.horizon.wrapper;

import com.brouck.horizon.generator.wrapper.QueryWrapper;
import com.brouck.horizon.session.HorizonSession;
import org.junit.Test;

/**
 * @author lts
 * Create time 2022/3/25
 */
public class WrapperTest {

    HorizonSession horizonSession;

    @Test
    public void searchWrapperTest() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", "张三");
        queryWrapper.gt("age", 18);

        User user = horizonSession.queryForObject(queryWrapper);
    }

}
