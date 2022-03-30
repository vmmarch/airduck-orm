package com.brouck.horizon.exception;

import com.brouck.horizon.commons.StringUtils;

/**
 * @author brouck
 * Create time 2022/3/29
 */
public class HorizonException extends RuntimeException {

    public HorizonException(String fmt, Object... args) {
        super(StringUtils.format(fmt, args));
    }

}
