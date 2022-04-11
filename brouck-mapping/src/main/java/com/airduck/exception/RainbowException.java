package com.airduck.exception;

import com.airduck.commons.StringUtils;

/**
 * @author brouck
 * Create time 2022/3/29
 */
public class RainbowException extends RuntimeException {

    public RainbowException(String fmt, Object... args) {
        super(StringUtils.format(fmt, args));
    }

}
