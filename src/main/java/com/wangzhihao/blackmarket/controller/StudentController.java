package com.wangzhihao.blackmarket.controller;

import com.wangzhihao.blackmarket.domain.Student;
import com.wangzhihao.blackmarket.domain.WechatUser;
import com.wangzhihao.blackmarket.dto.AddStudentDto;
import com.wangzhihao.blackmarket.dto.UpdateStudentDto;
import com.wangzhihao.blackmarket.exception.StudentNotFoundException;
import com.wangzhihao.blackmarket.service.StudentService;
import com.wangzhihao.blackmarket.utils.WechatUtils;
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
@RequestMapping(value = "/api/v1/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    WechatUtils wechatUtils;

    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseEntity getCurrentStudent() {
        WechatUser wechatUser = wechatUtils.requireWechatUser();
        return new ResponseEntity<>(studentService.getById(wechatUser.getId()), HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    ResponseEntity sendRegisterCode() {
        return new ResponseEntity<>("sendRegisterCode", HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    ResponseEntity createNewStudent(AddStudentDto addStudentDto) {
        WechatUser wechatUser = wechatUtils.requireWechatUser();
        addStudentDto.setId(wechatUser.getId());
        addStudentDto.setOpenId(wechatUser.getOpenId());
        Student student = new Student();
        student.setByAddStudentDto(addStudentDto);
        studentService.add(student);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    ResponseEntity updateCurrentStudent(@RequestBody UpdateStudentDto updateStudentDto) {
        if (!updateStudentDto.isEmpty()) {
            WechatUser wechatUser = wechatUtils.requireWechatUser();
            updateStudentDto.setId(wechatUser.getId());
            studentService.updateStudent(updateStudentDto);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        return new ResponseEntity<>("Nothing to update", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity getStudent(@PathVariable("id") long id) {
        wechatUtils.requireWechatUser();
        Student student = studentService.getById(id);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
        throw new StudentNotFoundException();
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
