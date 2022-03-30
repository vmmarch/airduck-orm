package com.brouck.horizon.exception;

/**
 * @author lts
 * Create time 2022/3/29
 */
public class IllegalTableClassException extends FormatException {
    public IllegalTableClassException(String fmt, Object... args) {
        super(fmt, args);
    }
}
