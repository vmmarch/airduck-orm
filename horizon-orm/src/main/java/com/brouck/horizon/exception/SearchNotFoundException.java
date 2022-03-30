package com.brouck.horizon.exception;

/**
 * @author brouck
 * Create time 2022/3/29
 */
public class SearchNotFoundException extends HorizonException {
    public SearchNotFoundException(String fmt, Object... args) {
        super(fmt, args);
    }
}
