package com.brouck.horizon.session.metadata;

import com.brouck.horizon.annotation.Column;
import com.brouck.horizon.annotation.ColumnComment;
import lombok.Data;

import java.lang.reflect.Field;

/**
 * 字段元数据
 *
 * @author lts
 * Create time 2022/3/29
 */
@Data
public class ColumnMetaData {

    /**
     * 字段名
     */
    private String name;

    /**
     * 字段长度，默认255
     */
    private int length;

    /**
     * 字段是否可为空
     */
    private boolean nullable;

    /**
     * 字段备注
     */
    private String columnComment;

    /**
     * 创建字段元数据
     */
    public ColumnMetaData(Column column, String name, Field field) {
        this.name = name;
        this.length = column.length();
        this.nullable = column.nullable();

        // 获取字段备注
        ColumnComment columnComment = field.getAnnotation(ColumnComment.class);
        if (columnComment != null)
            this.columnComment = columnComment.value();
    }

}
