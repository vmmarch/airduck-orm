package com.brouck.annotation;

import java.lang.annotation.*;

/**
 * 注解在主键上，为主键配置自动生成器。
 *
 * @author brouck
 * Create time 2022/3/24
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GeneratedValue {

    /**
     * Id生成器默认为数据库自增。然后可以自定义配置Id生成器，以 IdGeneratorFor 为前缀，后面跟着生成器自定义的名称。
     * 类似这样：IdGeneratorForXXXX, 比如：IdGeneratorForSequence
     *
     * id生成器可以自定义配置。只需要实现{@link IdGenerator}接口即可。
     */
    Class<? extends IdGenerator> generator();

}
