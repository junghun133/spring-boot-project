package com.pjh.web.shop.exception;

public class TypeConvertException extends RuntimeException {
    public TypeConvertException() {
        super();
    }

    public TypeConvertException(String message) {
        super(message);
    }

    public TypeConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    public TypeConvertException(Throwable cause) {
        super(cause);
    }
}
