package com.airduck;

import com.airduck.annotation.Column;
import com.airduck.annotation.Comment;
import com.airduck.annotation.Table;
import com.airduck.std.AirduckStdEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author airduck
 * Create time 2022/3/24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Comment("用户表")
@Table(name = "user")
public class User extends AirduckStdEntity {

    @Comment("用户名")
    @Column(length = 640, requiredNotNull = true)
    private String username;

    @Comment("密码")
    @Column(length = 16, requiredNotNull = true)
    private String password;

    @Comment("昵称")
    @Column(requiredNotNull = true, defaultValue = "nickname:={username}")
    private String nickname;

    @Comment("邮箱")
    @Column(length = 32, requiredNotNull = true)
    private String email;

    @Comment("状态")
    @Column(length = 1, requiredNotNull = true, defaultValue = "0")
    private Integer status;

}
