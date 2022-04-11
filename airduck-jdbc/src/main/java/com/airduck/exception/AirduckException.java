package com.airduck.exception;

import com.airduck.commons.StringUtils;

/**
 * @author airduck-vincent
 * Create time 2022/3/29
 */
public class AirduckException extends RuntimeException {

    public AirduckException(String fmt, Object... args) {
        super(StringUtils.format(fmt, args));
    }

}
