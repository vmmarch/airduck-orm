package com.brouck.horizon.exception;

/**
 * @author brouck
 * Create time 2022/3/23
 */
public class MultipleResultSetsException extends RuntimeException {

    public MultipleResultSetsException() {
    }

    public MultipleResultSetsException(String message) {
        super(message);
    }

    public MultipleResultSetsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MultipleResultSetsException(Throwable cause) {
        super(cause);
    }

    public MultipleResultSetsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
