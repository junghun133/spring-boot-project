package com.pjh.aed.aop;

import com.pjh.aed.dao.AuthDao;
import com.pjh.aed.service.executor.ServiceRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Slf4j
@Aspect
@Component
public class TokenValidAOP {
    final AuthDao authDao;

    public TokenValidAOP(AuthDao authDao) {
        this.authDao = authDao;
    }

    @Before("execution(* com.pjh.aed.service.executor.ServiceRunnerInterface.runService(..))")
    public void tokenValid(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        ServiceRequest request = (ServiceRequest) args[0];

        String token = request.getRequest().getToken();

        log.info("aop token: " + token);
        authDao.isToken(token);
    }
}
