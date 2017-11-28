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
@RequestMapping(value = "/api/v1/student")
public class StudentController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseEntity getCurrentStudent() {
        return new ResponseEntity<>("getCurrentStudent", HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    ResponseEntity sendRegisterCode() {
        return new ResponseEntity<>("sendRegisterCode", HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    ResponseEntity createNewStudent() {
        return new ResponseEntity<>("createNewStudent", HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    ResponseEntity updateCurrentStudent() {
        return new ResponseEntity<>("updateCurrentStudent", HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity getStudent(@PathVariable("id") long id) {
        return new ResponseEntity<>(String.format("getStudent No.%d", id), HttpStatus.OK);
    }

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    ResponseEntity getCurrentStudentPostList() {
        return new ResponseEntity<>("getCurrentStudentPostList", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/post", method = RequestMethod.GET)
    ResponseEntity getStudentPostList(@PathVariable("id") long id) {
        return new ResponseEntity<>(String.format("getStudent No.%d PostList", id), HttpStatus.OK);
    }

}
