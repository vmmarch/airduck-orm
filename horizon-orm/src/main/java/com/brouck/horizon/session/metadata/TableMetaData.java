package com.brouck.horizon.session.metadata;

import com.brouck.horizon.annotation.Column;
import com.brouck.horizon.annotation.Comment;
import com.brouck.horizon.annotation.Table;
import com.brouck.horizon.commons.Reflections;
import com.brouck.horizon.commons.StringUtils;
import com.brouck.horizon.exception.IllegalTableClassException;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 表信息的元数据
 *
 * @author brouck
 * Create time 2022/3/29
 */
@Getter
public class TableMetaData {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表备注
     */
    private String tableComment;

    /**
     * 获取实体class对象
     */
    private Class<?> entityClass;

    /**
     * 当前表的所有列名
     */
    private final Map<String, ColumnMetaData> columns = new LinkedHashMap<>();

    /**
     * 创建表元数据
     * @param tableClass 实体类
     */
    public TableMetaData(Class<?> tableClass) {
        if (!tableClass.isAnnotationPresent(Table.class)) {
            throw new IllegalTableClassException("创建表元数据失败，实体类必须包含@Table注解。");
        }

        parseMetaData(tableClass);
    }

    /**
     * 解析表元数据
     * @param tableClass 实体类
     */
    private void parseMetaData(Class<?> tableClass) {
        // 获取表名
        Table table = tableClass.getAnnotation(Table.class);
        this.tableName = table.name();

        // 获取表备注
        Comment comment = tableClass.getAnnotation(Comment.class);
        if (comment != null)
            this.tableComment = comment.value();

        // 获取所有字段
        var fields = Reflections.fields(tableClass);
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);

                if (column == null || column.unmap())
                    continue;

                String columnName = StringUtils.humpToUnderline(field.getName());
                columns.put(columnName, new ColumnMetaData(column, columnName, field));
            }
        }

        this.entityClass = tableClass;
    }

    /**
     * 获取实体类对象
     */
    public Class<?> getEntityClass() {
        return this.entityClass;
    }

    /**
     * @return 整张表的所有字段， 类似：username,password,nickname....
     */
    public String getAllQueryColumns() {
        var builder = new StringBuilder();
        for (var column : columns.keySet())
            builder.append(column).append(",");

        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

}
