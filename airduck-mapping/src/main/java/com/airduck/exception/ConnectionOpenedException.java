package com.airduck.exception;

/**
 * @author airduck-vincent
 * Create time 2022/3/23
 */
public class ConnectionOpenedException extends RainbowException {
    public ConnectionOpenedException(String fmt, Object... args) {
        super(fmt, args);
    }
}
