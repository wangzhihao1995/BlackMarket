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
@RequestMapping(value = "/api/v1/course/post")
public class CoursePostController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseEntity getCoursePostList() {
        return new ResponseEntity<>("get post list", HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    ResponseEntity getCoursePost(@PathVariable("id") long id) {
        return new ResponseEntity<>(String.format("Get No.%d post", id), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    ResponseEntity createNewCoursePost() {
        return new ResponseEntity<>("creatNewPost", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity updateCoursePost(@PathVariable("id") long id) {
        return new ResponseEntity<>(String.format("Update No.%d post", id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/status", method = RequestMethod.PUT)
    ResponseEntity updateCoursePostStatus(@PathVariable("id") long id) {
        return new ResponseEntity<>(String.format("Get No.%d post's status", id), HttpStatus.OK);
    }

}
