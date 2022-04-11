package com.airduck;

import com.airduck.annotation.Column;
import com.airduck.annotation.GeneratedValue;
import com.airduck.annotation.Id;
import com.airduck.generator.id.IdGeneratorForIncrement;
import lombok.Data;

import java.util.Date;

/**
 * @author brouck
 * Create time 2022/3/31
 */
@Data
public class BaseModel {

    @Id
    @Column(length = 64, nullable = false)
    @GeneratedValue(generator = IdGeneratorForIncrement.class)
    private Integer id;

    @Column
    private Date createTime;

    @Column
    private Date updateTime;

}
