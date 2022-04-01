package com.brouck.exception;

/**
 * @author brouck
 * Create time 2022/3/29
 */
public class SearchNotFoundException extends BrouckException {
    public SearchNotFoundException(String fmt, Object... args) {
        super(fmt, args);
    }
}
