package com.wangzhihao.blackmarket.controller;

import com.wangzhihao.blackmarket.dto.UpdateWechatUserDto;
import com.wangzhihao.blackmarket.service.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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

    @Autowired
    WechatUserService wechatUserService;

    @RequestMapping(value = "/jscode2session", method = RequestMethod.GET)
    ResponseEntity jscode2session() {
        return new ResponseEntity<>("Jscode2Session", HttpStatus.OK);
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
