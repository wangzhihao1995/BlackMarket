package com.wangzhihao.blackmarket.controller;

import com.google.common.collect.Lists;
import com.wangzhihao.blackmarket.domain.Course;
import com.wangzhihao.blackmarket.domain.CourseSchedule;
import com.wangzhihao.blackmarket.dto.CourseRespDto;
import com.wangzhihao.blackmarket.dto.GetCourseListDto;
import com.wangzhihao.blackmarket.service.CourseScheduleService;
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

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


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
    CourseScheduleService courseScheduleService;

    @Autowired
    WechatUtils wechatUtils;

    @ApiOperation(value = "Get Course List")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseEntity getCourseList(GetCourseListDto getCourseListDto) {
        wechatUtils.requireWechatUser();
        Integer year = getCourseListDto.getYear();
        Integer semester = getCourseListDto.getSemester();
        List<Course> courses = courseService.getListByYearAndSemester(year, semester);
        List<CourseRespDto> courseRespDtos = Lists.newArrayList();
        if (!courses.isEmpty()) {
            Set<Long> courseIds = courses.stream().map(Course::getId).collect(Collectors.toSet());
            Map<Long, List<CourseSchedule>> schedules = courseScheduleService.getCourseSchedulesByCourseIds(courseIds);
            for (Course course : courses) {
                CourseRespDto courseRespDto = new CourseRespDto();
                courseRespDto.setId(course.getId());
                courseRespDto.setName(course.getName());
                courseRespDto.setCredit(course.getCredit());
                courseRespDto.setTeacher(course.getTeacher());
                courseRespDto.setYear(course.getYear());
                courseRespDto.setSemester(course.getSemester());
                courseRespDto.setSchedules(schedules.get(course.getId()));
                courseRespDtos.add(courseRespDto);
            }
        }
        return new ResponseEntity<>(courseRespDtos, HttpStatus.OK);
    }

    @ApiOperation(value = "Get Course By ID")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity getCourse(@PathVariable("id") long id) {
        wechatUtils.requireWechatUser();
        Course course = courseService.getById(id);
        CourseRespDto courseRespDto = new CourseRespDto();
        courseRespDto.setId(course.getId());
        courseRespDto.setName(course.getName());
        courseRespDto.setCredit(course.getCredit());
        courseRespDto.setTeacher(course.getTeacher());
        courseRespDto.setYear(course.getYear());
        courseRespDto.setSemester(course.getSemester());
        courseRespDto.setSchedules(courseScheduleService.getCourseSchedulesByCourseId(course.getId()));
        return new ResponseEntity<>(courseRespDto, HttpStatus.OK);
    }
}
