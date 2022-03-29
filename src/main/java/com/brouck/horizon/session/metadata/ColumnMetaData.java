package com.brouck.horizon.session.metadata;

import com.alibaba.fastjson.annotation.JSONField;
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
    @JSONField(name = "COLUMN_NAME")
    private String name;

    /**
     * 是否可以为空
     */
    @JSONField(name = "IS_NULLABLE")
    private String nullable;

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
     * 创建字段元数据
     */
    public ColumnMetaData(Column column, String name, Field field) {
        this.name = name;
        this.length = column.length();
        this.nullable = String.valueOf(column.nullable());

        // 获取字段备注
        ColumnComment columnComment = field.getAnnotation(ColumnComment.class);
        if (columnComment != null)
            this.comment = columnComment.value();
    }

}
