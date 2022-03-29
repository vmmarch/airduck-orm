package com.brouck.horizon.exception;

/**
 * @author lts
 * Create time 2022/3/29
 */
public class SearchNotFoundException extends FormatException {
    public SearchNotFoundException(String fmt, Object... args) {
        super(fmt, args);
    }
}
