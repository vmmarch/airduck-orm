package com.airduck.exception;

/**
 * 不支持异常
 *
 * @author airduck-vincent
 * Create time 2022/4/11
 */
public class NotSupportException extends AirduckException {
    public NotSupportException(String fmt, Object... args) {
        super(fmt, args);
    }
}
