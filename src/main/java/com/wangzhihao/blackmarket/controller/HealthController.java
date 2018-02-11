package com.wangzhihao.blackmarket.controller;

import com.wangzhihao.blackmarket.enums.SmsVerificationTypeEnum;
import com.wangzhihao.blackmarket.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping(value = "/health")
public class HealthController {

    @Autowired
    SmsService smsService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseEntity health() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    ResponseEntity register() {
        return new ResponseEntity<>(
                smsService.add("15600000000", SmsVerificationTypeEnum.REGISTER), HttpStatus.OK);
    }

    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    ResponseEntity verify(@RequestParam String code) {
        return new ResponseEntity<>(
                smsService.verify("15600000000", code, SmsVerificationTypeEnum.REGISTER), HttpStatus.OK);
    }
}
