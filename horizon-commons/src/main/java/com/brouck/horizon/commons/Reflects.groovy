package com.brouck.horizon.commons

import java.lang.reflect.Field

/**
 * 反射工具类
 *
 * @author lts
 * Create time 2022/3/31
 */
class Reflects {

    /**
     * 获取类的所有成员对象
     */
    static List<Field> fields(Class<?> _class) {
        def fields = new LinkedList()

        Class<?> superClass = _class
        while((superClass = superClass.getSuperclass()) != Object.class) {
            fields.addAll(List.of(superClass.declaredFields))
        }

        fields.addAll(List.of(_class.declaredFields))
        return fields
    }

}
