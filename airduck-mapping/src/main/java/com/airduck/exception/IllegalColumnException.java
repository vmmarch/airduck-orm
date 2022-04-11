package com.airduck.exception;

/**
 * @author airduck-vincent
 * Create time 2022/3/29
 */
public class IllegalColumnException extends AirduckException {
    public IllegalColumnException(String fmt, Object... args) {
        super(fmt, args);
    }
}
