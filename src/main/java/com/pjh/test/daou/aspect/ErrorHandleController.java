package com.pjh.test.daou.aspect;

import com.pjh.test.daou.exception.NotEnoughStockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class ErrorHandleController {

    /*@ExceptionHandler(Exception.class)
    public String commonError(){
        return "error/500";
    }

    @ExceptionHandler(NotEnoughStockException.class)
    public String notEnoughStock(Exception e){
        return e.getMessage();
    }*/
    @ExceptionHandler(NotEnoughStockException.class)
    public String notEnoughStock(Exception e){
        log.error("ttt");
        return e.getMessage();
    }
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
        log.error("cause: " + e.getMessage());
        log.error("=================================================================");
        return "error/500";
    }
}
