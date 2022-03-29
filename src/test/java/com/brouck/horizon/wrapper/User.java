package com.brouck.horizon.wrapper;

import com.alibaba.fastjson.annotation.JSONField;
import com.brouck.horizon.annotation.*;
import com.brouck.horizon.generator.id.IdGeneratorForSnowflake;
import lombok.Data;

/**
 * @author brouck
 * Create time 2022/3/24
 */
@Data
@TableRemarks("用户表")
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(generator = IdGeneratorForSnowflake.class)
    private String id;

    @ColumnRemarks("用户名")
    @Column(nullable = false)
    private String username;

    @Column
    private String nickname;

    @Column(length = 528, nullable = false)
    private String comment;

    @Column(map = false)
    private Integer queryCount;

}
