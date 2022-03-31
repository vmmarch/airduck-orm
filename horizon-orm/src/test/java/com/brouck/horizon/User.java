package com.brouck.horizon;

import com.brouck.horizon.annotation.*;
import com.brouck.horizon.extend.HorizonObject;
import com.brouck.horizon.generator.id.IdGeneratorForIncrement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author brouck
 * Create time 2022/3/24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Comment("用户表")
@Table(name = "user")
public class User extends BaseModel {

    @Comment("用户名")
    @Column(nullable = false)
    private String username;

    @Column
    private String nickname;

    @Column(length = 528, nullable = false)
    private String comment;

}
