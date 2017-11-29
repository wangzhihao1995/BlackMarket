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
@RequestMapping(value = "/api/v1/wechat")
public class WechatController {

    @RequestMapping(value = "/jscode2session", method = RequestMethod.GET)
    ResponseEntity jscode2session() {
        return new ResponseEntity<>("Jscode2Session", HttpStatus.OK);
    }


    @RequestMapping(value = "/check_session", method = RequestMethod.GET)
    ResponseEntity checkSession() {
        return new ResponseEntity<>("Check Session", HttpStatus.OK);
    }


    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    ResponseEntity updateWecahtUser() {
        return new ResponseEntity<>("user", HttpStatus.OK);
    }
}
