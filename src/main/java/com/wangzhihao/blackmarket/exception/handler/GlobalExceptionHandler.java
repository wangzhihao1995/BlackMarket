package com.wangzhihao.blackmarket.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/12/6.
 *
 * @author Wang Zhihao.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR = "error";
    private static final String ERROR_MSG = "caught exception";
    private static final String MESSAGE = "message";
    private static final String TYPE = "type";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        logger.error(ERROR_MSG, e);
        Map map = new HashMap();
        map.put(MESSAGE, e.getMessage());
        map.put(TYPE, Exception.class.getName());
        Map output = new HashMap();
        output.put(ERROR, map);
        return new ResponseEntity<>(output, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity handleHttpClientErrorException(HttpClientErrorException e) {
        logger.info(ERROR_MSG, e);
        Map map = new HashMap();
        map.put(MESSAGE, e.getResponseBodyAsString());
        map.put(TYPE, HttpClientErrorException.class.getName());
        Map output = new HashMap();
        output.put(ERROR, map);
        return new ResponseEntity<>(output, e.getStatusCode());
    }
}
