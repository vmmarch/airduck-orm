package com.brouck.horizon.exception;

/**
 * @author brouck
 * Create time 2022/3/23
 */
public class ConnectionOpenedException extends HorizonException {
    public ConnectionOpenedException(String fmt, Object... args) {
        super(fmt, args);
    }
}
