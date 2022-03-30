package com.brouck.horizon.exception;

/**
 * @author brouck
 * Create time 2022/3/29
 */
public class IllegalTableClassException extends HorizonException {
    public IllegalTableClassException(String fmt, Object... args) {
        super(fmt, args);
    }
}
