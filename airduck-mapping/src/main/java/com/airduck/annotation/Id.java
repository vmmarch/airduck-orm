package com.airduck.annotation;

import java.lang.annotation.*;

/**
 * 注解在成员上，注解一个主键
 *
 * @author airduck
 * Create time 2022/3/24
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Id {

}
