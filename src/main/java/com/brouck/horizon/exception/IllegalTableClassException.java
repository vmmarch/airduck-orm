package com.brouck.horizon.exception;

/**
 * @author lts
 * Create time 2022/3/29
 */
public class IllegalTableClassException extends RuntimeException {

    public IllegalTableClassException() {
    }

    public IllegalTableClassException(String message) {
        super(message);
    }

    public IllegalTableClassException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalTableClassException(Throwable cause) {
        super(cause);
    }

    public IllegalTableClassException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
