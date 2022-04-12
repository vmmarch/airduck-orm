package com.airduck.std;

import com.airduck.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 表对象标准类，包含（ID、创建时间、更新时间、数据是否禁用）等基础字段。
 *
 * @author airduck-vincent
 * Create time 2022/3/31
 */
@Data
public class AirduckStdEntity {

    @Id
    @IdGeneratedValue(generator = IdGenerator.INCREMENT)
    @Comment("ID")
    @Column(requiredNotNull = true)
    private Long id;

    /**
     * 创建时间，当数据创建时会自动设置创建时间。并且创建时间不可修改。也无需手动设置。
     */
    @Comment("创建时间")
    @Column(requiredNotNull = true)
    private Date createTime;

    /**
     * 更新时间，当数据更新时会自动设置更新时间。无需手动设置。
     */
    @Comment("更新时间")
    @Column(requiredNotNull = true)
    private Date updateTime;

}
