package com.airduck.exception;

/**
 * @author brouck
 * Create time 2022/3/23
 */
public class MultipleResultSetsException extends RainbowException {
    public MultipleResultSetsException(String fmt, Object... args) {
        super(fmt, args);
    }
}
