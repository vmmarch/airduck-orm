package com.airduck.annotation;

import java.lang.annotation.*;

/**
 * 注解在类上，表示一个实体映射类
 *
 * @author airduck
 * Create time 2022/3/24
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Table {

    /**
     * 对应的映射表名
     */
    String name();

}
