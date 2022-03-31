package com.brouck.horizon.tools;

import com.brouck.horizon.exception.HorizonException;
import com.brouck.horizon.extend.SuperEntity;

/**
 * 针对于Horizon-ORM项目的工具类，并不适用于其他项目
 *
 * @author lts
 * Create time 2022/3/31
 */
public class HorizonAsserts {

    /**
     * 检查对象是否合法，比如HorizonORM框架要求每个实体对象都必须继承{@link SuperEntity}对象。
     * 如果没有集成表示不合法。
     */
    public static void checkObject(Object object) {
        includeSuperEntity(object.getClass());
    }

    /**
     * 检查对象所有父类是否包含{@link SuperEntity}对象
     */
    public static void includeSuperEntity(Class<?> aClass) {
        var source = aClass;
        while((aClass = aClass.getSuperclass()) != Object.class) {
            if (aClass == SuperEntity.class)
                return;
        }

        throw new HorizonException("{}实体类不合法，请检查实体类是否继承了SuperEntity", source.getName());
    }

}
