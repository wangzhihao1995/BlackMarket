package com.wangzhihao.blackmarket.aspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/10/16.
 *
 * @author Wang Zhihao.
 */
@Aspect
@Component
public class WebLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(* com.wangzhihao.blackmarket.controller.*.*(..))")
    public void webLog() {
        // do nothing
    }

    @Before("webLog()")
    public void beforeMethod(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        JSONObject json = new JSONObject();
        json.put("URL", request.getRequestURL().toString());
        json.put("HTTP_METHOD", request.getMethod());
        json.put("CLASS_METHOD", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature()
                .getName());
        json.put("ARGS", Arrays.toString(joinPoint.getArgs()));

        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = enu.nextElement();
            json.put("param", paraName + "_" + request.getParameter(paraName));
        }
        logger.info("BlackMarketRequest:{}", json);
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(JoinPoint joinPoint, Object ret) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        int status = response.getStatus();
        JSONObject json = new JSONObject();
        json.put("RESPONSE", ret);
        json.put("HTTP_STATUS_CODE", status);
        json.put("TIME_COST", System.currentTimeMillis() - startTime.get());
        logger.info("BlackMarketResponse:{}", json);
        startTime.remove();
    }
}
