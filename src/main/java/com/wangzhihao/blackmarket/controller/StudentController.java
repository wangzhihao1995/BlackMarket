package com.wangzhihao.blackmarket.controller;

import com.wangzhihao.blackmarket.domain.Student;
import com.wangzhihao.blackmarket.domain.WechatUser;
import com.wangzhihao.blackmarket.dto.AddStudentDto;
import com.wangzhihao.blackmarket.dto.RegisterDto;
import com.wangzhihao.blackmarket.dto.UpdateStudentDto;
import com.wangzhihao.blackmarket.enums.SmsVerificationTypeEnum;
import com.wangzhihao.blackmarket.exception.StudentNotFoundException;
import com.wangzhihao.blackmarket.service.SmsService;
import com.wangzhihao.blackmarket.service.StudentService;
import com.wangzhihao.blackmarket.utils.WechatUtils;
import com.google.common.collect.Maps;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping(value = "/api/student")
public class StudentController {

    private static final String VERIFY_CODE_TEMPLATE = "【NSD黑市】欢迎使用BlackMarket，您的手机验证码是%s。本条信息无需回复";

    @Autowired
    StudentService studentService;

    @Autowired
    SmsService smsService;

    @Autowired
    WechatUtils wechatUtils;

    @Value("${blackmarket.config.debug}")
    private Boolean debug;

    @ApiOperation(value = "Get Current Student")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseEntity getCurrentStudent() {
        WechatUser wechatUser = wechatUtils.requireWechatUser();
        Student student = studentService.getByWechatUserId(wechatUser.getId());
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @ApiOperation(value = "Send Register Code")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    ResponseEntity sendRegisterCode(@RequestBody RegisterDto registerDto) {
        wechatUtils.requireWechatUser();
        String mobile = registerDto.getMobile();
        String verificationCode = smsService.add(mobile, SmsVerificationTypeEnum.REGISTER);
        String message = String.format(VERIFY_CODE_TEMPLATE, verificationCode);
        if (!debug) {
            Result<SmsSingleSend> result = smsService.singleSend(mobile, message);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ApiOperation(value = "Create New Student")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "", method = RequestMethod.POST)
    ResponseEntity createNewStudent(@RequestBody AddStudentDto addStudentDto) {
        WechatUser wechatUser = wechatUtils.requireWechatUser();
        addStudentDto.setWechatUserId(wechatUser.getId());
        addStudentDto.setOpenId(wechatUser.getOpenId());
        Student student = new Student();
        student.setWechatUserId(addStudentDto.getWechatUserId());
        student.setName(addStudentDto.getName());
        student.setMobile(addStudentDto.getMobile());
        student.setOpenId(addStudentDto.getOpenId());
        student.setType(addStudentDto.getType());
        student.setGrade(addStudentDto.getGrade());
        student.setStatus(addStudentDto.getStatus());
        studentService.add(student);
        return new ResponseEntity<>(Maps.newHashMap(), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update Current Student")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "", method = RequestMethod.PUT)
    ResponseEntity updateCurrentStudent(@RequestBody UpdateStudentDto updateStudentDto) {
        if (!updateStudentDto.isEmpty()) {
            WechatUser wechatUser = wechatUtils.requireWechatUser();
            updateStudentDto.setId(wechatUser.getId());
            studentService.updateStudent(updateStudentDto);
            return new ResponseEntity<>(Maps.newHashMap(), HttpStatus.OK);
        }
        return new ResponseEntity<>(Maps.newHashMap(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get Studnet By ID")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity getStudentById(@PathVariable("id") long id) {
        wechatUtils.requireWechatUser();
        Student student = studentService.getById(id);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
        throw new StudentNotFoundException();
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/post", method = RequestMethod.GET)
    ResponseEntity getCurrentStudentPostList() {
        wechatUtils.requireWechatUser();
        return new ResponseEntity<>("getCurrentStudentPostList", HttpStatus.OK);
    }

    @ApiOperation(value = "Get Student Post List")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/{id}/post", method = RequestMethod.GET)
    ResponseEntity getStudentPostList(@PathVariable("id") long id) {
        wechatUtils.requireWechatUser();
        return new ResponseEntity<>(String.format("getStudent No.%d PostList", id), HttpStatus.OK);
    }
}
