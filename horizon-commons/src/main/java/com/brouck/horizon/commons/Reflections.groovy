package com.brouck.horizon.commons


import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 * 反射工具类
 *
 * @author lts
 * Create time 2022/3/31
 */
class Reflections {

    /**
     * 获取类的所有成员对象
     */
    static List<Field> fields(Class<?> _class) {
        def fields = new LinkedList()

        Class<?> superClass = _class
        while ((superClass = superClass.getSuperclass()) != Object.class) {
            fields.addAll(List.of(superClass.declaredFields))
        }

        fields.addAll(List.of(_class.declaredFields))
        return fields
    }

    /**
     * 获取一个函数的反射对象，搜索所有类包括父类的。
     * 优先从当前类查找，如果找不到会从父类去找。直到找到Object.class类后停止。
     */
    static Method searchMethods(Class<?> source, String name, Class<?>... args) {
        // 搜索当前类
        var method = searchMethod(source, name, args)

        // 搜索父类
        while ((source = source.superclass) != Object.class) {
            method = searchMethod(source, name, args)
            if (method)
                break
        }

        return method
    }

    /**
     * 从当前类搜索对应的函数对象
     *
     * @param source 类对象
     * @param name 函数名
     * @param args 函数参数
     */
    static searchMethod(Class<?> source, String name, Class<?>... args) {
        return source.declaredMethods.find {
            it.name == name && it.parameterTypes == args
        }
    }

}
