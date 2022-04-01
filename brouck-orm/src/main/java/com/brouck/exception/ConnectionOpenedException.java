package com.brouck.exception;

/**
 * @author brouck
 * Create time 2022/3/23
 */
public class ConnectionOpenedException extends BrouckException {
    public ConnectionOpenedException(String fmt, Object... args) {
        super(fmt, args);
    }
}
