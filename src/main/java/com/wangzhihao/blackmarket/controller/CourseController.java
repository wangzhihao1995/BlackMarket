package com.wangzhihao.blackmarket.controller;

import com.wangzhihao.blackmarket.domain.Course;
import com.wangzhihao.blackmarket.dto.GetCourseListDto;
import com.wangzhihao.blackmarket.service.CourseService;
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
@RequestMapping(value = "/api/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    WechatUtils wechatUtils;

    @ApiOperation(value = "Get Course List")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseEntity getCourseList(GetCourseListDto getCourseListDto) {
        wechatUtils.requireWechatUser();
        Integer year = getCourseListDto.getYear();
        String semester = getCourseListDto.getSemester();
        return new ResponseEntity<>(courseService.getListByYearAndSemester(year, semester), HttpStatus.OK);
    }

    @ApiOperation(value = "Get Course By ID")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity getCourse(@PathVariable("id") long id) {
        wechatUtils.requireWechatUser();
        Course course = courseService.getById(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }
}
