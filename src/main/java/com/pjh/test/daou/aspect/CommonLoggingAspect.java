package com.pjh.test.daou.aspect;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static com.pjh.test.daou.aspect.CommonLoggingValue.*;

@Aspect
@Component
@Slf4j
public class CommonLoggingAspect {
    //controller public method 포함
    @Pointcut("within(com.pjh.test.daou.controller..*)")
    public void controllerPointcut() {}


    @Around("controllerPointcut()")
    public Object doLogging(ProceedingJoinPoint joinPoint) throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String controllerName = joinPoint.getSignature().getDeclaringType().getName();
        String methodName = joinPoint.getSignature().getName();
        Map<String, Object> logs = new HashMap<>();

        try{

            logs.put(Controller.name(), controllerName);
            logs.put(Method.name(), methodName);
            logs.put(Params.name(), getRequestParams(request));
            logs.put(LogTime.name(), LocalDateTime.now());
            logs.put(RequestUrl.name(), request.getRequestURI());
            logs.put(HttpMethod.name(), request.getMethod());
        }catch (Exception e){
            log.error("Common logging aspect exception: " + e);
        }
        log.info("=====================================");
        log.info("Controller : {}", logs);
        log.info("=====================================");

        return joinPoint.proceed();
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
}
