package com.brouck.horizon.generator.table;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 字段信息实体
 *
 * @author lts
 * Create time 2022/3/29
 */
@Data
public class InColumn {

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
    private String length;

    /**
     * 是不是主键
     */
    @JSONField(name = "COLUMN_KEY")
    private String key;


}
