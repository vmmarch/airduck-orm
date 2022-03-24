package com.brouck.horizon.exception;

/**
 * @author brouck
 * Create time 2022/3/23
 */
public class WrongOperationException extends RuntimeException {
    public WrongOperationException() {
    }

    public WrongOperationException(String message) {
        super(message);
    }

    public WrongOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongOperationException(Throwable cause) {
        super(cause);
    }

    public WrongOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
