package com.airduck.exception;

/**
 * @author airduck
 * Create time 2022/3/29
 */
public class IllegalTableClassException extends RainbowException {
    public IllegalTableClassException(String fmt, Object... args) {
        super(fmt, args);
    }
}
