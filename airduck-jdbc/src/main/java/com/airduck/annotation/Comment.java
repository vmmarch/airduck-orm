package com.airduck.annotation;

import java.lang.annotation.*;

/**
 * 注解在类上和字段上
 *
 * @author airduck-vincent
 * Create time 2022/3/24
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Comment {

    /**
     * 字段描述
     */
    String value() default "";

}
