package com.brouck.exception;

/**
 * @author brouck
 * Create time 2022/3/23
 */
public class MultipleResultSetsException extends BrouckException {
    public MultipleResultSetsException(String fmt, Object... args) {
        super(fmt, args);
    }
}
