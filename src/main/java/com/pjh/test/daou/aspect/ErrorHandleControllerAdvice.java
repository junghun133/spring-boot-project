package com.pjh.test.daou.aspect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class ErrorHandleControllerAdvice {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    /**
     * 런타임 에러 -> 서비스 에러 처리
     * @param e
     * @param req
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public String handlerRuntimeException(RuntimeException e, HttpServletRequest req) {
        log.error("==================== handlerRuntimeException ====================");
        log.error("date: " + LocalDateTime.now());
        e.printStackTrace(pw);
        log.error("cause: " + sw.toString());
        log.error("=================================================================");
        return "error/500";
    }
}
