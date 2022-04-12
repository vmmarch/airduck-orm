package com.airduck.exception;

/**
 * @author airduck-vincent
 * Create time 2022/3/29
 */
public class IllegalException extends AirduckException {
    public IllegalException(String fmt, Object... args) {
        super(fmt, args);
    }
}
