package com.pjh.test.daou.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ErrorHandleController {

    @ExceptionHandler(Exception.class)
    public String commonError(){
        return "error/500";
    }
}
