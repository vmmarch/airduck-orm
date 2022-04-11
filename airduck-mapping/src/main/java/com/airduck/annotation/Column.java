package com.airduck.annotation;

import java.lang.annotation.*;

/**
 * 注解在成员上，代表一个表字段
 *
 * @author airduck-vincent
 * Create time 2022/3/24
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {

    /**
     * 字段长度，如果是varchar类型默认长度为255。
     * 如果是int类型默认长度为11
     */
    int length() default 255;

    /**
     * 是否允许为空
     */
    boolean requiredNotNull() default false;

    /**
     * 默认值
     */
    String defaultValue() default "";

}
