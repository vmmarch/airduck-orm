package com.airduck.session.airducksession.properties;

import com.airduck.annotation.*;
import com.airduck.commons.StringUtils;
import com.airduck.session.airducksession.SupportBy;
import com.airduck.std.AirduckStdEntity;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.lang.reflect.Field;

/**
 * 字段属性
 *
 * @author airduck-vincent
 * Create time 2022/4/11
 */
@Data
public class ColumnAttributes {

    /**
     * java的驼峰命名
     */
    private String javaName;

    /**
     * 数据库的下划线名称
     */
    private String underlineName;

    /**
     * Java字段类型
     */
    @JSONField(serialize = false)
    private Field javaField;

    /**
     * 数据库字段类型
     */
    private String type;

    /**
     * 字段长度
     */
    private int length;

    /**
     * 不能为空
     */
    private boolean requiredNotNull;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 是不是主键
     */
    private boolean isPrimaryKey = false;

    /**
     * 如果是主键的话使用的id生成器
     */
    private IdGenerator idGenerator = null;

    /**
     * 备注
     */
    private String comment;

    /**
     * 所属表
     */
    private String table;

    /**
     * 通用SQL
     */
    private String commonSQL;

    /**
     * 字段更新语句
     */
    private String alterSQL;

    /**
     * 检查字段是否符合要求
     */
    public static boolean checkColumnField(Field field) {
        return field.isAnnotationPresent(Column.class);
    }

    /**
     * 构造器
     */
    private ColumnAttributes(Field field, String table) {
        this.table = table;

        // id解析
        if (field.isAnnotationPresent(Id.class)) {
            this.isPrimaryKey = true;
            if (field.isAnnotationPresent(IdGeneratedValue.class)) {
                this.idGenerator = field.getAnnotation(IdGeneratedValue.class)
                        .generator();
            }
        }

        // java字段类型
        this.javaField = field;
        // 数据库字段类型
        this.type = SupportBy.MYSQL.typeMap(javaField.getType());
        // java的驼峰命名
        this.javaName = javaField.getName();
        // 数据库的下划线名称
        this.underlineName = StringUtils.humpToUnderline(javaName);

        // 备注
        if (field.isAnnotationPresent(Comment.class)) {
            Comment comment = field.getAnnotation(Comment.class);
            this.comment = comment.value();
        }

        // 字段注解解析
        var column = field.getAnnotation(Column.class);
        if (column.length() == 0xffffffff) {
            this.length = 0;
            // 设置整型和字符型的默认长度
            if (javaField.getType().getSuperclass() == Number.class) {
                this.length = 11;
            } else if (javaField.getType() == String.class) {
                this.length = 255;
            }
        } else {
            this.length = column.length();
        }

        this.requiredNotNull = column.requiredNotNull();

        // 获取字段的备注
        if (javaField.isAnnotationPresent(Comment.class)) {
            this.comment = javaField.getAnnotation(Comment.class).value();
        }

        // 通用sql
        this.buildCommonSQL();
    }

    /**
     * 构建通用sql
     */
    private void buildCommonSQL() {
        commonSQL = "`"+ underlineName + "` "
                + type + /* 这里预留一个转义。如果类型是字符串或者整数需要设置长度 */"{} "
                + (requiredNotNull ? "NOT NULL" : "")
                + (defaultValue != null ? "DEFAULT " + defaultValue : "")
                + (isPrimaryKey ? "PRIMARY KEY" : "")
                + (comment != null ? " COMMENT '" + comment + "'" : "");

        if (javaField.getType() == String.class || javaField.getType().getSuperclass() == Number.class) {
            commonSQL = StringUtils.format(commonSQL, "(" + length + ")");
        } else {
            commonSQL = StringUtils.format(commonSQL, "");
        }
    }

    /**
     * 创建字段属性
     *
     * @param field                java对象的成员反射类
     * @param isOverridePrimaryKey 是否覆盖AirduckStdEntity的主键
     */
    public static ColumnAttributes create(String table, boolean isOverridePrimaryKey, Field field) {
        if (!field.isAnnotationPresent(Column.class))
            return null;

        if (field.isAnnotationPresent(Id.class)) {
            if (field.getDeclaringClass() == AirduckStdEntity.class && isOverridePrimaryKey) {
                return null;
            }
        }

        return new ColumnAttributes(field, table);
    }

}
