package com.wangzhihao.blackmarket.controller;

import com.wangzhihao.blackmarket.utils.WechatUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping(value = "/api/share")
public class ShareController {

    @Autowired
    WechatUtils wechatUtils;

    @ApiOperation(value = "Share Course Post")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/coursepost", method = RequestMethod.POST)
    ResponseEntity shareCoursePost() {
        wechatUtils.requireWechatUser();
        return new ResponseEntity<>("shareCoursePost", HttpStatus.OK);
    }

    @ApiOperation(value = "Share Goods Post")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/goodspost", method = RequestMethod.POST)
    ResponseEntity shareGoodsPost() {
        wechatUtils.requireWechatUser();
        return new ResponseEntity<>("shareGoodsPost", HttpStatus.OK);
    }

    @ApiOperation(value = "Share Current Student")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/student", method = RequestMethod.POST)
    ResponseEntity shareCurrentStudent() {
        wechatUtils.requireWechatUser();
        return new ResponseEntity<>("shareCurrentStudent", HttpStatus.OK);
    }

    @ApiOperation(value = "Share Student File")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/student/{id}/image", method = RequestMethod.GET)
    ResponseEntity getShareStudentImage(@PathVariable("id") long id) {
        wechatUtils.requireWechatUser();
        return new ResponseEntity<>(String.format("get Student No.%d Share File", id), HttpStatus.OK);
    }

    @ApiOperation(value = "Get Share Course Post File")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/coursepost/{id}/image", method = RequestMethod.GET)
    ResponseEntity getShareCoursePostImage(@PathVariable("id") long id) {
        wechatUtils.requireWechatUser();
        return new ResponseEntity<>(String.format("get No.%d Course Post ShareImage", id), HttpStatus.OK);
    }

    @ApiOperation(value = "Get Share Goods Post File")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/goodspost/{id}/image", method = RequestMethod.GET)
    ResponseEntity getShareGoodsPostImage(@PathVariable("id") long id) {
        wechatUtils.requireWechatUser();
        return new ResponseEntity<>("get No.%d GoodsPost Share File", HttpStatus.OK);
    }
}
