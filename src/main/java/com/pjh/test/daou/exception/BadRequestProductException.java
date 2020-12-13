package com.pjh.test.daou.exception;

public class BadRequestProductException extends RuntimeException {
    public BadRequestProductException() {
        super();
    }

    public BadRequestProductException(String message) {
        super(message);
    }

    public BadRequestProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestProductException(Throwable cause) {
        super(cause);
    }
}
