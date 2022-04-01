package com.brouck.tools;

import com.brouck.annotation.Table;
import com.brouck.commons.StringUtils;
import com.brouck.exception.BrouckException;
import com.brouck.extend.SuperRecord;

/**
 * 针对于Horizon-ORM项目的工具类，并不适用于其他项目
 *
 * @author brouck
 * Create time 2022/3/31
 */
public class BrouckUtils {

    /**
     * 检查对象是否合法，比如HorizonORM框架要求每个实体对象都必须继承{@link SuperRecord}对象。
     * 如果没有集成表示不合法。
     */
    public static void checkObject(Object object) {
        includeSuperEntity(object.getClass());

        Class<?> _class = object.getClass();
        if (!_class.isAnnotationPresent(Table.class))
            throw new BrouckException("{}实体对象需要添加@Table注解，否则不允许操作。", _class.getName());

    }

    /**
     * 检查对象所有父类是否包含{@link SuperRecord}对象
     */
    public static void includeSuperEntity(Class<?> aClass) {
        var source = aClass;
        while((aClass = aClass.getSuperclass()) != Object.class) {
            if (aClass == SuperRecord.class)
                return;
        }

        throw new BrouckException("{}实体类不合法，请检查实体类是否继承了SuperEntity", source.getName());
    }

    /**
     * 获取实体对象表名
     * @param object 实体对象
     */
    public static String getTableName(Object object) {
        return getTableName(object.getClass());
    }

    /**
     * 获取实体类对象表名
     * @param source 实体类对象
     */
    public static String getTableName(Class<?> source) {
        if (source.isAnnotationPresent(Table.class)) {
            Table annotation = source.getAnnotation(Table.class);
            return annotation.name();
        }

        throw new BrouckException("未获取到@Table注解，请检查{}实体类是否添加了@Table注解", source.getName());
    }

    /**
     * 生成value的参数转义符
     */
    public static String getParamValue(int size) {
        return "values(" + StringUtils.join(",", size, "?") + ")";
    }

}
