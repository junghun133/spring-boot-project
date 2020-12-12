package com.pjh.test.daou.exception;

import lombok.extern.slf4j.Slf4j;

public class ResultNotFoundException extends RuntimeException {

    public ResultNotFoundException() {
        super();
    }

    public ResultNotFoundException(String message) {
        super(message);
    }

    public ResultNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResultNotFoundException(Throwable cause) {
        super(cause);
    }
}
