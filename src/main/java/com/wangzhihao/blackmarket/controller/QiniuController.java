package com.wangzhihao.blackmarket.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
@RestController
@RequestMapping(value = "/api/v1/qiniu")
public class QiniuController {

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    ResponseEntity getToken() {
        return new ResponseEntity<>("getToken", HttpStatus.OK);
    }

    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    ResponseEntity qiniuCallBack() {
        return new ResponseEntity<>("qiniuCallBack", HttpStatus.OK);
    }
}
