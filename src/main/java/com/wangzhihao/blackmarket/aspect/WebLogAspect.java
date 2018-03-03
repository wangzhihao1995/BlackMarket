package com.wangzhihao.blackmarket.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public void webServiceLog() {
        // empty
    }

    @Pointcut("execution(* com.wangzhihao.blackmarket.exception.*.*.*(..))")
    public void errorLog() {
        // empty
    }

    @Before("webServiceLog()")
    public void beforeRequest(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        JSONObject json = new JSONObject();
        json.put("URL", request.getRequestURL().toString());
        json.put("HTTP_METHOD", request.getMethod());
        json.put("CLASS_METHOD", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature()
                .getName());

        List<Map<String, String>> args = Lists.newArrayList();
        Enumeration<String> parameterNames = request.getParameterNames();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (parameterNames.hasMoreElements()) {
            String paraName = parameterNames.nextElement();
            Map<String, String> arg = new HashMap<>();
            arg.put(paraName, request.getParameter(paraName));
            args.add(arg);
        }
        json.put("ARGS", args);

        List<Map<String, String>> headers = Lists.newArrayList();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            Map<String, String> header = new HashMap<>();
            header.put(headerName, request.getHeader(headerName));
            headers.add(header);
        }
        json.put("HEADERS", headers);
        json.put("BODY", parseRequestBody(request));
        logger.info("Request:{}", json);
    }

    @AfterReturning(returning = "ret", pointcut = "webServiceLog()")
    public void doAfterNormalReturning(JoinPoint joinPoint, Object ret) {
        doAfterReturning(joinPoint, ret);
    }

    @AfterReturning(returning = "ret", pointcut = "errorLog()")
    public void doAfterExceptionReturning(JoinPoint joinPoint, Object ret) {
        doAfterReturning(joinPoint, ret);
    }

    @SuppressWarnings("squid:S1172")
    private void doAfterReturning(JoinPoint joinPoint, Object ret) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        int status = response.getStatus();
        JSONObject json = new JSONObject();
        json.put("RESPONSE_DATA", ret);
        json.put("HTTP_STATUS", status);
        json.put("TIME_COST", System.currentTimeMillis() - startTime.get());
        logger.info("Response:{}", json);
        startTime.remove();
    }

    @SuppressWarnings("squid:S1166")
    private String parseRequestBody(HttpServletRequest request) {
        try {
            String bodyString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            JSONObject bodyJson = JSON.parseObject(bodyString);
            return bodyJson.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
