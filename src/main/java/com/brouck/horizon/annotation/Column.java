package com.brouck.horizon.annotation;

import java.lang.annotation.*;

/**
 * 注解在成员上，代表一个表字段
 *
 * @author brouck
 * Create time 2022/3/24
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {

    /**
     * varchar类型默认长度为255，
     * int类型默认长度为1
     */
    int length() default -1;

    /**
     * 设置字段不允许为空，默认允许为空
     */
    boolean nullable() default true;

    /**
     * 设置当前字段不与表映射
     */
    boolean unmap() default false;

}
