package com.wangzhihao.blackmarket.controller;

import com.wangzhihao.blackmarket.dto.QiniuCallbackDto;
import com.wangzhihao.blackmarket.service.QiniuService;
import com.wangzhihao.blackmarket.utils.WechatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping(value = "/api/qiniu")
public class QiniuController {

    @Autowired
    QiniuService qiniuService;

    @Autowired
    WechatUtils wechatUtils;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    ResponseEntity getToken() {
        wechatUtils.requireWechatUser();
        return new ResponseEntity<>(qiniuService.fetchToken(1L), HttpStatus.OK);
    }

    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    ResponseEntity qiniuCallBack(@RequestBody QiniuCallbackDto qiniuCallbackDto) {
        wechatUtils.requireWechatUser();
        return new ResponseEntity<>("qiniuCallBack", HttpStatus.OK);
    }
}
