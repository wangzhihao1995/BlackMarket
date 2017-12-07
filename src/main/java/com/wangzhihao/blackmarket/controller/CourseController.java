package com.wangzhihao.blackmarket.controller;

import com.wangzhihao.blackmarket.domain.Course;
import com.wangzhihao.blackmarket.dto.GetCourseListDto;
import com.wangzhihao.blackmarket.service.CourseService;
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
@RequestMapping(value = "/api/v1/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseEntity getCourseList(GetCourseListDto getCourseListDto) {
        Long year = getCourseListDto.getYear();
        String semester = getCourseListDto.getSemester();
        return new ResponseEntity<>(courseService.getListByYearAndSemester(year, semester), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity getCourse(@PathVariable("id") long id) {
        Course course = courseService.getById(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }
}
