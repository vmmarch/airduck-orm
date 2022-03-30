package com.brouck.horizon.exception;

/**
 * @author brouck
 * Create time 2022/3/23
 */
public class MultipleResubroucketsException extends RuntimeException {

    public MultipleResubroucketsException() {
    }

    public MultipleResubroucketsException(String message) {
        super(message);
    }

    public MultipleResubroucketsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MultipleResubroucketsException(Throwable cause) {
        super(cause);
    }

    public MultipleResubroucketsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
