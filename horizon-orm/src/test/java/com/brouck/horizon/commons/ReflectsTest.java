package com.brouck.horizon.commons;

import com.brouck.horizon.User;
import org.junit.Test;

/**
 * @author lts
 * Create time 2022/3/31
 */
public class ReflectsTest {

    @Test
    public void fields() {
        Reflects.fields(User.class);
    }

}
