package com.brouck.horizon.query;

import com.brouck.horizon.annotation.*;
import com.brouck.horizon.generator.id.IdGeneratorForIncrement;
import com.brouck.horizon.generator.id.IdGeneratorForSnowflake;
import lombok.Data;

import java.util.Date;

/**
 * @author brouck
 * Create time 2022/3/24
 */
@Data
@TableComment("用户表")
@Table(name = "user")
public class User {

    @Id
    @Column(length = 64, nullable = false)
    @GeneratedValue(generator = IdGeneratorForIncrement.class)
    private String id;

    @ColumnComment("用户名")
    @Column(nullable = false)
    private String username;

    @Column
    private String nickname;

    @Column(length = 528, nullable = false)
    private String comment;

    @Column
    private Date createTime;

    @Column
    private Date updateTime;

    @Column(unmap = true)
    private Integer queryCount;

}
