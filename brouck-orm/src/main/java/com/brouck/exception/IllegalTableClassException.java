package com.brouck.exception;

/**
 * @author brouck
 * Create time 2022/3/29
 */
public class IllegalTableClassException extends BrouckException {
    public IllegalTableClassException(String fmt, Object... args) {
        super(fmt, args);
    }
}
