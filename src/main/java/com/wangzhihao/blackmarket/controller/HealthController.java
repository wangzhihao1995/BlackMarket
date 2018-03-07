package com.wangzhihao.blackmarket.controller;

import com.google.common.collect.Maps;
import com.wangzhihao.blackmarket.domain.WechatSession;
import com.wangzhihao.blackmarket.enums.SmsVerificationTypeEnum;
import com.wangzhihao.blackmarket.service.SmsService;
import com.wangzhihao.blackmarket.service.WechatSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    WechatSessionService wechatSessionService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseEntity health() {
        Map<String, String> r = new HashMap<>();
        r.put("status", "ok");
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @RequestMapping(value = "/key", method = RequestMethod.GET)
    ResponseEntity getKey() {
        WechatSession wechatSession = wechatSessionService.getByOpenId("o-irt0KDNL3neAUoa3HiKDnIxc_c");
        if (wechatSession != null) {
            return new ResponseEntity<>(wechatSession, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
