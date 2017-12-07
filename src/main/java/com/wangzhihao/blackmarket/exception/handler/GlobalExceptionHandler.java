package com.wangzhihao.blackmarket.exception.handler;

import com.wangzhihao.blackmarket.exception.BlackMarketException;
import com.wangzhihao.blackmarket.exception.MissingSessionKeyException;
import com.wangzhihao.blackmarket.exception.WechatSessionNotFoundException;
import com.wangzhihao.blackmarket.exception.WechatUserNotFoundException;
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

    private Object genOutputException(BlackMarketException exception) {
        OutputErrorInfo outputException = new OutputErrorInfo(exception.getMessage(), exception.getCode(), exception
                .getTraceId(), exception.getType());
        Map<String, OutputErrorInfo> map = new HashMap<>();
        map.put(ERROR, outputException);
        return map;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        logger.error(ERROR_MSG, e);
        Map<String, String> map = new HashMap<>();
        map.put(MESSAGE, e.getMessage());
        map.put(TYPE, Exception.class.getName());
        Map<String, Object> output = new HashMap<>();
        output.put(ERROR, map);
        return new ResponseEntity<>(output, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity handleHttpClientErrorException(HttpClientErrorException e) {
        logger.info(ERROR_MSG, e);
        Map<String, String> map = new HashMap<>();
        map.put(MESSAGE, e.getResponseBodyAsString());
        map.put(TYPE, HttpClientErrorException.class.getName());
        Map<String, Object> output = new HashMap<>();
        output.put(ERROR, map);
        return new ResponseEntity<>(output, e.getStatusCode());
    }

    @ExceptionHandler(MissingSessionKeyException.class)
    public ResponseEntity handleMissingSessionKeyException(MissingSessionKeyException e) {
        logger.info(ERROR_MSG, e);
        e.setMessage("Missing Session Key");
        e.setCode("1001");
        e.setType(MissingSessionKeyException.class.getName());
        Object resp = genOutputException(e);
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WechatSessionNotFoundException.class)
    public ResponseEntity handleWechatSessionNotFoundException(WechatSessionNotFoundException e) {
        logger.info(ERROR_MSG, e);
        e.setMessage("Wechat Session Not Found");
        e.setCode("1002");
        e.setType(WechatSessionNotFoundException.class.getName());
        Object resp = genOutputException(e);
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WechatUserNotFoundException.class)
    public ResponseEntity handleWechatUserNotFoundException(WechatUserNotFoundException e) {
        logger.info(ERROR_MSG, e);
        e.setMessage("Wechat User Not Found");
        e.setCode("1003");
        e.setType(WechatUserNotFoundException.class.getName());
        Object resp = genOutputException(e);
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }
}
