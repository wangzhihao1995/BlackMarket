package com.wangzhihao.blackmarket.controller;

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
@RequestMapping(value = "/api/v1/share")
public class ShareController {

    @RequestMapping(value = "/coursepost", method = RequestMethod.POST)
    ResponseEntity shareCoursePost() {
        return new ResponseEntity<>("shareCoursePost", HttpStatus.OK);
    }

    @RequestMapping(value = "/goodspost", method = RequestMethod.POST)
    ResponseEntity shareGoodsPost() {
        return new ResponseEntity<>("shareGoodsPost", HttpStatus.OK);
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    ResponseEntity shareCurrentStudent() {
        return new ResponseEntity<>("shareCurrentStudent", HttpStatus.OK);
    }

    @RequestMapping(value = "/student/{id}/image", method = RequestMethod.GET)
    ResponseEntity getShareStudentImage(@PathVariable("id") long id) {
        return new ResponseEntity<>(String.format("get Student No.%d Share Image", id), HttpStatus.OK);
    }

    @RequestMapping(value = "/coursepost/{id}/image", method = RequestMethod.GET)
    ResponseEntity getShareCoursePostImage(@PathVariable("id") long id) {
        return new ResponseEntity<>(String.format("get No.%d Course Post ShareImage", id), HttpStatus.OK);
    }

    @RequestMapping(value = "/goodspost/{id}/image", method = RequestMethod.GET)
    ResponseEntity getShareGoodsPostImage(@PathVariable("id") long id) {
        return new ResponseEntity<>("get No.%d GoodsPost Share Image", HttpStatus.OK);
    }
}
