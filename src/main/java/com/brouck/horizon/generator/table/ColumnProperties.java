package com.brouck.horizon.generator.table;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 字段信息实体
 *
 * @author lts
 * Create time 2022/3/29
 */
public class ColumnProperties {

    private String field;
    private String type;
    private String collation;
    @JSONField(name = "Null")
    private String isNull;
    private String key;
    @JSONField(name = "default")
    private String _default;
    private String extra;
    private String privileges;
    private String comment;


}
