package com.brouck.horizon.exception;

/**
 * @author lts
 * Create time 2022/3/23
 */
public class ConnectionOpenedException extends RuntimeException{

    public ConnectionOpenedException() {
    }

    public ConnectionOpenedException(String message) {
        super(message);
    }

    public ConnectionOpenedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionOpenedException(Throwable cause) {
        super(cause);
    }

    public ConnectionOpenedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
