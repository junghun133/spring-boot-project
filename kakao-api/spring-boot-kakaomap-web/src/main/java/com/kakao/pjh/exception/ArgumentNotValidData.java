package com.kakao.pjh.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Data
public class ArgumentNotValidData {
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ArgumentNotValidData(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ArgumentNotValidData(HttpStatus status, String message, String error) {
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
}
