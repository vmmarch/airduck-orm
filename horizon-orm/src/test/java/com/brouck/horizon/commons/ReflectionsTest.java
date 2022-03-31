package com.brouck.horizon.commons;

import com.brouck.horizon.User;
import org.junit.Test;

/**
 * @author lts
 * Create time 2022/3/31
 */
public class ReflectionsTest {

    @Test
    public void fields() {
        Reflections.fields(User.class);
    }

}
