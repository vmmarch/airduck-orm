package com.brouck;

import com.brouck.annotation.Column;
import com.brouck.annotation.Comment;
import com.brouck.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

    @Column(nullable = false)
    private String nickname;

    @Column(length = 528, nullable = false)
    private String comment;

}
