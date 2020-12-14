package com.pjh.test.daou.exception;

public class NotProvideProductTypeExcpetion extends RuntimeException {
    public NotProvideProductTypeExcpetion() {
        super();
    }

    public NotProvideProductTypeExcpetion(String message) {
        super(message);
    }

    public NotProvideProductTypeExcpetion(String message, Throwable cause) {
        super(message, cause);
    }

    public NotProvideProductTypeExcpetion(Throwable cause) {
        super(cause);
    }
}
