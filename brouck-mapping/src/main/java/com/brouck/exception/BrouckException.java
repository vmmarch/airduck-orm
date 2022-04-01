package com.brouck.exception;

import com.brouck.commons.StringUtils;

/**
 * @author brouck
 * Create time 2022/3/29
 */
public class BrouckException extends RuntimeException {

    public BrouckException(String fmt, Object... args) {
        super(StringUtils.format(fmt, args));
    }

}
