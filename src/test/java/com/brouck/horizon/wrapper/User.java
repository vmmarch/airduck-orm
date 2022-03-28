package com.brouck.horizon.wrapper;

import com.brouck.horizon.annotation.*;
import com.brouck.horizon.generator.id.IdGeneratorForSnowflake;

/**
 * @author brouck
 * Create time 2022/3/24
 */
@TableRemarks("用户表")
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(generator = IdGeneratorForSnowflake.class)
    private String id;

    @ColumnRemarks("用户名")
    @Column(nullable = false)
    private String username;

    @Column(name = "nickname")
    private String alias;

    @Column(name = "comment", length = 528, nullable = false)
    private String comment;

    @Column(map = false)
    private Integer queryCount;

}
