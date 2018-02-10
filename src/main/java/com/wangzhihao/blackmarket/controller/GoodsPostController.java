package com.wangzhihao.blackmarket.controller;

import com.wangzhihao.blackmarket.utils.WechatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(value = "/api/goods/post")
public class GoodsPostController {

    @Autowired
    WechatUtils wechatUtils;

    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseEntity getPostList() {
        wechatUtils.requireWechatUser();
        return new ResponseEntity<>("get post list", HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    ResponseEntity getGoodsPost(@PathVariable("id") long id) {
        wechatUtils.requireWechatUser();
        return new ResponseEntity<>(String.format("Get No.%d post", id), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    ResponseEntity createNewGoodsPost() {
        wechatUtils.requireWechatUser();
        return new ResponseEntity<>("creatNewPost", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity updateGoodsPost(@PathVariable("id") long id) {
        wechatUtils.requireWechatUser();
        return new ResponseEntity<>(String.format("Update No.%d post", id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/status", method = RequestMethod.PUT)
    ResponseEntity updateGoodsPostStatus(@PathVariable("id") long id) {
        wechatUtils.requireWechatUser();
        return new ResponseEntity<>(String.format("Get No.%d post's status", id), HttpStatus.OK);
    }

}
