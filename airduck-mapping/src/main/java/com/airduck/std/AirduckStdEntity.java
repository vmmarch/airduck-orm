package com.airduck.std;

import com.airduck.annotation.Column;
import com.airduck.annotation.GeneratedValue;
import com.airduck.annotation.Id;
import com.airduck.generator.id.IdGeneratorForIncrement;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author airduck
 * Create time 2022/3/31
 */
@Data
public class AirduckStdEntity {

    @Id
    @Column()
    @GeneratedValue(generator = IdGeneratorForIncrement.class)
    private Integer id;

    @Column()
    private Date createTime;

    @Column()
    private Date updateTime;

}
