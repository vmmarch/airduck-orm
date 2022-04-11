package com.airduck.commons;

import java.util.Date;

/**
 * 时间枚举
 *
 * @author brouck
 * Create time 2022/3/30
 */
public enum TimeUnit {
    SECOND, MINUTE, HOUR, DAY, MONTH, YEAR;

    /**
     * 增加时间
     */
    public Date plus(Date date, int number) {
        return DateUtils.plus(date, number, this);
    }

    /**
     * 减少时间
     */
    public Date minus(Date date, int number) {
        return DateUtils.minus(date, number, this);
    }

}
