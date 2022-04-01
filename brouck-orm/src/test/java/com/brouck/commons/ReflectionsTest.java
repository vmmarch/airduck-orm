package com.brouck.commons;

import com.brouck.User;
import org.junit.Test;

/**
 * @author brouck
 * Create time 2022/3/31
 */
public class ReflectionsTest {

    @Test
    public void fields() {
        Reflections.searchFields(User.class);
    }

}
