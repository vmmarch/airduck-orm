package com.airduck.exception;

/**
 * @author airduck-vincent
 * Create time 2022/3/29
 */
public class SearchNotFoundException extends RainbowException {
    public SearchNotFoundException(String fmt, Object... args) {
        super(fmt, args);
    }
}
