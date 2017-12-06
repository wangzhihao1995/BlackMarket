package com.wangzhihao.blackmarket.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wangzhihao.blackmarket.dto.UpdateWechatUserDto;
import com.wangzhihao.blackmarket.service.WechatService;
import com.wangzhihao.blackmarket.service.WechatUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;


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

    private static final String OPENID = "openid";
    private static final String SESSION_KEY = "session_key";


    @Autowired
    WechatService wechatService;

    @Autowired
    WechatUserService wechatUserService;

    @RequestMapping(value = "/jscode2session", method = RequestMethod.GET)
    ResponseEntity jscode2session(@Param("code") String code) {
        JSONObject res = JSON.parseObject(wechatService.jscode2session(code));
        String openId = (String) res.get(OPENID);
        String sessionKey = (String) res.get(SESSION_KEY);
        String thirdSessionKey = openId + sessionKey;
        HashMap<String, String> map = new HashMap<>();
        map.put(SESSION_KEY, thirdSessionKey);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/check_session", method = RequestMethod.GET)
    ResponseEntity checkSession() {
        return new ResponseEntity<>("Check Session", HttpStatus.OK);
    }


    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    ResponseEntity updateWecahtUser(@Valid @RequestBody UpdateWechatUserDto updateWechatUserDto) {
        wechatUserService.updateWechatUser(updateWechatUserDto);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    ResponseEntity getWechatUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(wechatUserService.getById(id), HttpStatus.OK);
    }
}
