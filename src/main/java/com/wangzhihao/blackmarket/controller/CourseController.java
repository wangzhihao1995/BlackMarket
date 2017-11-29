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
@RequestMapping(value = "/api/v1/course")
public class CourseController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseEntity getCourseList() {
        return new ResponseEntity<>("getCourseList", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity getCourse(@PathVariable("id") long id) {
        return new ResponseEntity<>("getCourse", HttpStatus.OK);
    }
}
