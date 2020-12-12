package com.pjh.test.daou.exception;

public class NotEnothStockException extends RuntimeException {
    public NotEnothStockException() {
        super();
    }

    public NotEnothStockException(String message) {
        super(message);
    }

    public NotEnothStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnothStockException(Throwable cause) {
        super(cause);
    }
}
