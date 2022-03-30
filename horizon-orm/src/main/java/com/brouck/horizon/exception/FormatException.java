package com.brouck.horizon.exception;

import com.brouck.horizon.tools.StringUtils;

/**
 * @author lts
 * Create time 2022/3/29
 */
public class FormatException extends RuntimeException {

    public FormatException(String fmt, Object... args) {
        super(StringUtils.format(fmt, args));
    }

}
