package com.airduck.annotation;

import com.airduck.std.AirduckStdEntity;

import java.lang.annotation.*;

/**
 * 注解在table类上，表示不使用{@link AirduckStdEntity}的主键
 *
 * @see AirduckStdEntity
 * @author airduck-vincent
 * Create time 2022/3/24
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OverridePrimaryKey {
}
