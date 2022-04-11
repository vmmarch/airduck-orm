package com.airduck.exception;

/**
 * @author airduck-vincent
 * Create time 2022/3/29
 */
public class IllegalTableClassException extends AirduckException {
    public IllegalTableClassException(String fmt, Object... args) {
        super(fmt, args);
    }
}
