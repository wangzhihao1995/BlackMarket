package com.wangzhihao.blackmarket.controller.index;

import com.google.common.collect.Maps;
import com.wangzhihao.blackmarket.domain.ConfigDomain;
import com.wangzhihao.blackmarket.domain.WechatSession;
import com.wangzhihao.blackmarket.enums.SmsVerificationTypeEnum;
import com.wangzhihao.blackmarket.service.ConfigService;
import com.wangzhihao.blackmarket.service.SmsService;
import com.wangzhihao.blackmarket.service.WechatSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
@RestController
@RequestMapping(value = "/index")
public class IndexController {

    @Autowired
    SmsService smsService;

    @Autowired
    WechatSessionService wechatSessionService;

    @Autowired
    ConfigService configService;

    @RequestMapping(value = "/pageview/{pageview}", method = RequestMethod.PUT)
    ResponseEntity incrPageView(@PathVariable Long pageview) {
        String key = "black:market:index:page:view";
        ConfigDomain config = configService.getByKey(key);
        if (config == null) {
            config = new ConfigDomain();
            config.setK(key);
            config.setV(String.valueOf(pageview));
            configService.add(config);
        }
        if (pageview < Long.parseLong(config.getV())) {
            Long val = Long.parseLong(config.getV()) + 1;
            configService.update(key, String.valueOf(val));
            return new ResponseEntity<>(Maps.immutableEntry("page_view", pageview), HttpStatus.OK);
        }
        configService.update(key, String.valueOf(pageview));
        return new ResponseEntity<>(Maps.immutableEntry("page_view", pageview), HttpStatus.OK);
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
