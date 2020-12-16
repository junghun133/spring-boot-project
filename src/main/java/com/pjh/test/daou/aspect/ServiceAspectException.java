package com.pjh.test.daou.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceAspectException {

    //Service package aspect
    @Around("execution(* com.pjh.test.daou.service.*.*(..))")
    public Object serviceExceptionHandler(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            return proceedingJoinPoint.proceed();
        } catch(Throwable e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
