package com.brouck.horizon.annotation;

import java.lang.annotation.*;

/**
 * 注解在类上，对于表做描述使用
 *
 * @author brouck
 * Create time 2022/3/24
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableRemarks {

    /**
     * 字段描述
     */
    String value() default "";

}
