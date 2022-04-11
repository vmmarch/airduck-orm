package com.airduck.exception;

/**
 * @author airduck-vincent
 * Create time 2022/3/23
 */
public class MultipleResultSetsException extends RainbowException {
    public MultipleResultSetsException(String fmt, Object... args) {
        super(fmt, args);
    }
}
