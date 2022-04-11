package com.airduck.annotation;

import java.lang.annotation.*;

/**
 * 注解在主键上，为主键配置自动生成器。
 *
 * @author airduck-vincent
 * Create time 2022/3/24
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IdGeneratedValue {

    /**
     * Id生成器默认为数据库自增。
     */
    IdGenerator generator() default IdGenerator.INCREMENT;

}
