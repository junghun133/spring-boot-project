package com.pjh.test.daou.aspect;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Enumeration;

import static com.pjh.test.daou.aspect.CommonLoggingValue.ControllerLog.*;
import static com.pjh.test.daou.aspect.CommonLoggingValue.ServiceLog.*;
import static com.pjh.test.daou.aspect.CommonLoggingValue.TimeLog.*;

@Aspect
@Component
@Slf4j
public class CommonLoggingAspect {
    //controller public method 포함
    @Pointcut("within(com.pjh.test.daou.controller..*)")
    public void controllerPointcut() {}

    @Pointcut("within(com.pjh.test.daou.service..*)")
    public void servicePointcut() {}

    @Around("controllerPointcut()")
    public Object doControllerLogging(ProceedingJoinPoint joinPoint) throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String controllerName = joinPoint.getSignature().getDeclaringType().getName();
        String methodName = joinPoint.getSignature().getName();

        log.info("=======================================================================================");
        logging(ControllerLog.name(), ControllerName.name(), controllerName);
        logging(ControllerLog.name(),Method.name(), methodName);
        logging(ControllerLog.name(),Params.name(), getRequestParams(request));
        logging(ControllerLog.name(),LogTime.name(), LocalDateTime.now());
        logging(ControllerLog.name(),RequestUrl.name(), request.getRequestURI());
        logging(ControllerLog.name(),HttpMethod.name(), request.getMethod());

        return joinPoint.proceed();
    }

    @Before("servicePointcut()")
    public void doServiceLogging(JoinPoint joinPoint){
        log.info("=======================================================================================");
        logging(ServiceLog.name(), Arguments.name(), Arrays.toString(joinPoint.getArgs()));
        logging(ServiceLog.name(),Kind.name(), joinPoint.getKind());
        logging(ServiceLog.name(),Signature.name(), joinPoint.getSignature().getName());
        logging(ServiceLog.name(),Target.name(), joinPoint.getTarget().toString());
    }

    @Around("servicePointcut()")
    public Object doServiceTimeLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("=======================================================================================");
        LocalDateTime startTime = LocalDateTime.now();

        //process target
        Object result = joinPoint.proceed();

        LocalDateTime endTime = LocalDateTime.now();

        logging(TimeLog.name(), StartTime.name(), startTime);
        logging(TimeLog.name(), EndTime.name(), endTime);
        logging(TimeLog.name(), ExecutionTime.name(), Duration.between(startTime, endTime));
        return result;
    }

    private static JSONObject getRequestParams(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        Enumeration<String> params = request.getParameterNames();

        while(params.hasMoreElements()){
            String param = params.nextElement();
            String replaceParam = param.replaceAll("\\.", "-");
            jsonObject.put(replaceParam, request.getParameter(param));
        }

        return jsonObject;
    }

    private void logging(String logType, String targetName, Object targetValue){
        log.info(logType  + " {} : {}", targetName, targetValue);
    }
}
