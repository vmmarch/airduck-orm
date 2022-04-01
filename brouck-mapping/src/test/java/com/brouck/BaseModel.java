package com.brouck;

import com.brouck.annotation.Column;
import com.brouck.annotation.GeneratedValue;
import com.brouck.annotation.Id;
import com.brouck.extend.SuperRecord;
import com.brouck.generator.id.IdGeneratorForIncrement;
import lombok.Data;

import java.util.Date;

/**
 * @author brouck
 * Create time 2022/3/31
 */
@Data
public class BaseModel extends SuperRecord {

    @Id
    @Column(length = 64, nullable = false)
    @GeneratedValue(generator = IdGeneratorForIncrement.class)
    private Integer id;

    @Column
    private Date createTime;

    @Column
    private Date updateTime;

}
