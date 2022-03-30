package com.brouck.horizon.session.metadata;

import com.alibaba.fastjson.annotation.JSONField;
import com.brouck.horizon.annotation.*;
import com.brouck.horizon.exception.HorizonException;
import com.brouck.horizon.generator.id.IdGeneratorForIncrement;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Locale;

/**
 * 字段元数据
 *
 * @author brouck
 * Create time 2022/3/29
 */
@Data
public class ColumnMetaData {

    /**
     * 字段名
     */
    @JSONField(name = "COLUMN_NAME")
    private String name;

    /**
     * 是否可以为空，String类型是为了整合数据库的返回结果。
     */
    @JSONField(name = "IS_NULLABLE")
    private boolean nullable;

    /**
     * 字段类型
     */
    @JSONField(name = "DATA_TYPE")
    private String type;

    /**
     * 字段长度
     */
    @JSONField(name = "CHARACTER_MAXIMUM_LENGTH")
    private int length;

    /**
     * 是不是主键
     */
    @JSONField(name = "COLUMN_KEY")
    private String key;

    private String comment;

    /**
     * 是否是主键
     */
    private boolean primaryKey;

    /**
     * 主键生成器
     */
    private Class<?> generatedValue;

    /** 空的构造器 */
    public ColumnMetaData() {}

    /**
     * 创建字段元数据
     */
    public ColumnMetaData(Column column, String name, Field field) {
        this.name = name;
        this.length = column.length();
        setNullable(column.nullable());

        // 获取字段备注
        Comment columnComment = field.getAnnotation(Comment.class);
        if (columnComment != null)
            this.comment = columnComment.value();

        // 解析字段类型
        Class<?> type = field.getType();
        if (type == String.class) {
            this.type = "varchar";
            this.length = this.length == 0 ? 255 : this.length;
        } else if (type == Integer.class || type == Long.class) {
            this.type = "int";
            this.length = this.length == 0 ? 10 : this.length;
        } else if (type == Date.class) {
            this.type = "datetime";
        }

        // 判断当前字段是不是主键
        if (field.isAnnotationPresent(Id.class)) {
            this.primaryKey = true;

            // 获取Id生成器
            if (field.isAnnotationPresent(GeneratedValue.class)) {
                GeneratedValue generatedValue = field.getAnnotation(GeneratedValue.class);
                this.generatedValue = generatedValue.generator();

                if (this.generatedValue == IdGeneratorForIncrement.class && !this.type.equals("int")) {
                    throw new HorizonException("{}#{}字段要使用自增长Id生成器必须确保字段的类型是Int类型，而非其他类型。",
                            field.getDeclaringClass().getName(), field.getName());
                }
            }

        }
    }

}
