package com.wangzhihao.blackmarket.exception.handler;

import com.wangzhihao.blackmarket.exception.*;
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

    @ExceptionHandler(MissingJscodeException.class)
    public ResponseEntity handleMissingJscodeException(MissingJscodeException e) {
        logger.info(ERROR_MSG, e);
        e.setMessage("Missing Jscode");
        e.setCode("1001");
        e.setType(MissingJscodeException.class.getName());
        Object resp = genOutputException(e);
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Jscode2SessionException.class)
    public ResponseEntity handleJscode2SessionException(Jscode2SessionException e) {
        logger.info(ERROR_MSG, e);
        e.setMessage(e.getMessage());
        e.setCode("1002");
        e.setType(Jscode2SessionException.class.getName());
        Object resp = genOutputException(e);
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingSessionKeyException.class)
    public ResponseEntity handleMissingSessionKeyException(MissingSessionKeyException e) {
        logger.info(ERROR_MSG, e);
        e.setMessage("Missing Session Key");
        e.setCode("1003");
        e.setType(MissingSessionKeyException.class.getName());
        Object resp = genOutputException(e);
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WechatSessionNotFoundException.class)
    public ResponseEntity handleWechatSessionNotFoundException(WechatSessionNotFoundException e) {
        logger.info(ERROR_MSG, e);
        e.setMessage("Wechat Session Not Found");
        e.setCode("1004");
        e.setType(WechatSessionNotFoundException.class.getName());
        Object resp = genOutputException(e);
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WechatUserNotFoundException.class)
    public ResponseEntity handleWechatUserNotFoundException(WechatUserNotFoundException e) {
        logger.info(ERROR_MSG, e);
        e.setMessage("Wechat User Not Found");
        e.setCode("1005");
        e.setType(WechatUserNotFoundException.class.getName());
        Object resp = genOutputException(e);
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity handleStudentNotFoundException(StudentNotFoundException e) {
        logger.info(ERROR_MSG, e);
        e.setMessage("Student Not Found");
        e.setCode("1006");
        e.setType(StudentNotFoundException.class.getName());
        Object resp = genOutputException(e);
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity handleCourseNotFoundException(CourseNotFoundException e) {
        logger.info(ERROR_MSG, e);
        e.setMessage("Course Not Found");
        e.setCode("1007");
        e.setType(CourseNotFoundException.class.getName());
        Object resp = genOutputException(e);
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(CoursePostNotFoundException.class)
    public ResponseEntity handleCoursePostNotFoundException(CoursePostNotFoundException e) {
        logger.info(ERROR_MSG, e);
        e.setMessage("Course Post Not Found");
        e.setCode("1008");
        e.setType(CoursePostNotFoundException.class.getName());
        Object resp = genOutputException(e);
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GoodsPostNotFoundException.class)
    public ResponseEntity handleGoodsPostNotFoundException(GoodsPostNotFoundException e) {
        logger.info(ERROR_MSG, e);
        e.setMessage("Goods Post Not Found");
        e.setCode("1009");
        e.setType(GoodsPostNotFoundException.class.getName());
        Object resp = genOutputException(e);
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }
}
