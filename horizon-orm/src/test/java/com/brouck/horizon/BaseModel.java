package com.brouck.horizon;

import com.brouck.horizon.annotation.Column;
import com.brouck.horizon.annotation.GeneratedValue;
import com.brouck.horizon.annotation.Id;
import com.brouck.horizon.extend.SuperRecord;
import com.brouck.horizon.generator.id.IdGeneratorForIncrement;
import lombok.Data;

import java.util.Date;

/**
 * @author lts
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
