package com.brouck.horizon.annotation;

import java.lang.annotation.*;

/**
 * 注解在成员上，对于字段做描述使用
 *
 * @author brouck
 * Create time 2022/3/24
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ColumnComment {

    /**
     * 字段描述
     */
    String value() default "";

}
