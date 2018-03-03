package com.wangzhihao.blackmarket.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wangzhihao.blackmarket.domain.Course;
import com.wangzhihao.blackmarket.domain.CoursePost;
import com.wangzhihao.blackmarket.domain.Student;
import com.wangzhihao.blackmarket.domain.WechatUser;
import com.wangzhihao.blackmarket.dto.*;
import com.wangzhihao.blackmarket.enums.CoursePostStautsEnumBlackMarket;
import com.wangzhihao.blackmarket.exception.AddCoursePostException;
import com.wangzhihao.blackmarket.exception.CoursePostNotFoundException;
import com.wangzhihao.blackmarket.exception.UpdateCoursePostException;
import com.wangzhihao.blackmarket.service.CoursePostService;
import com.wangzhihao.blackmarket.service.CourseService;
import com.wangzhihao.blackmarket.service.StudentService;
import com.wangzhihao.blackmarket.service.WechatUserService;
import com.wangzhihao.blackmarket.utils.WechatUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
@RestController
@RequestMapping(value = "/api/course/post")
public class CoursePostController {

    @Autowired
    CoursePostService coursePostService;

    @Autowired
    CourseService courseService;

    @Autowired
    StudentService studentService;

    @Autowired
    WechatUserService wechatUserService;

    @Autowired
    WechatUtils wechatUtils;

    private void validCoursePost(AddCoursePostDto addCoursePostDto) {
        GetCoursePostListDto getCoursePostListDto = new GetCoursePostListDto();
        getCoursePostListDto.setStudentId(addCoursePostDto.getStudentId());
        getCoursePostListDto.setSupply(addCoursePostDto.getSupply());
        getCoursePostListDto.setDemand(addCoursePostDto.getDemand());
        getCoursePostListDto.setStatus(CoursePostStautsEnumBlackMarket.NORMAL.getValue());
        getCoursePostListDto.setLimit(1L);
        List<CoursePost> ids = coursePostService.getCoursePostList(getCoursePostListDto);
        if (!ids.isEmpty()) {
            throw new AddCoursePostException("Already existed!");
        }
    }

    private List<CoursePostResp> buildCoursePostResps(List<CoursePost> coursePosts) {
        List<CoursePostResp> resps = Lists.newArrayList();
        for (CoursePost coursePost : coursePosts) {
            CoursePostResp coursePostResp = new CoursePostResp();
            coursePostResp.setId(coursePost.getId());
            Student student = studentService.getById(coursePost.getStudentId());
            WechatUser wechatUser = wechatUserService.getByOpenId(student.getOpenId());

            StudentResp studentResp = new StudentResp();
            studentResp.setId(student.getId());
            studentResp.setUsername(wechatUser.getNickName());
            studentResp.setMobile(student.getMobile());
            studentResp.setType(student.getType());
            studentResp.setGrade(student.getGrade());
            studentResp.setStatus(student.getStatus());
            studentResp.setAvatarUrl(wechatUser.getAvatarUrl());
            studentResp.setCreateTime(student.getCreateTime());
            studentResp.setUpdateTime(student.getUpdateTime());

            coursePostResp.setStudent(studentResp);
            coursePostResp.setDemand(courseService.getById(coursePost.getDemand()));
            coursePostResp.setSupply(courseService.getById(coursePost.getSupply()));
            coursePostResp.setStatus(coursePost.getStatus());
            coursePostResp.setMobileSwitch(coursePost.getMobileSwitch());
            coursePostResp.setWechat(coursePost.getWechat());
            coursePostResp.setMessage(coursePost.getMessage());
            coursePostResp.setPv(coursePost.getPv());
            coursePostResp.setCreateTime(coursePost.getCreateTime());
            coursePostResp.setUpdateTime(coursePost.getUpdateTime());

            resps.add(coursePostResp);
        }
        return resps;
    }

    @ApiOperation(value = "Get Course Post List")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseEntity getCoursePostList(GetCoursePostListDto getCoursePostListDto) {
        if (getCoursePostListDto.getDemand().equals(0L)) {
            getCoursePostListDto.setDemand(null);
        }
        if (getCoursePostListDto.getSupply().equals(0L)) {
            getCoursePostListDto.setSupply(null);
        }
        List<CoursePost> coursePosts = coursePostService.getCoursePostList(getCoursePostListDto);
        List<CoursePostResp> resps = buildCoursePostResps(coursePosts);
        return new ResponseEntity<>(resps, HttpStatus.OK);
    }

    @ApiOperation(value = "Get Course Post List")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/mine", method = RequestMethod.GET)
    ResponseEntity getMyCoursePostList(GetCoursePostListDto getCoursePostListDto) {
        WechatUser wechatUser = wechatUtils.requireWechatUser();
        Student student = studentService.getByOpenId(wechatUser.getOpenId());
        getCoursePostListDto.setStudentId(student.getId());
        if (getCoursePostListDto.getDemand().equals(0L)) {
            getCoursePostListDto.setDemand(null);
        }
        if (getCoursePostListDto.getSupply().equals(0L)) {
            getCoursePostListDto.setSupply(null);
        }
        List<CoursePost> coursePosts = coursePostService.getCoursePostList(getCoursePostListDto);
        List<CoursePostResp> resps = buildCoursePostResps(coursePosts);
        return new ResponseEntity<>(resps, HttpStatus.OK);
    }

    @ApiOperation(value = "Get Course Post By ID")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity getCoursePostById(@PathVariable("id") long id) {
        WechatUser currentWechatUser = wechatUtils.requireWechatUser();
        Student currentStudent = studentService.getByOpenId(currentWechatUser.getOpenId());

        CoursePost coursePost = coursePostService.getById(id);
        if (coursePost != null) {
            Long pv = coursePostService.incrPv(coursePost);
            Student student = studentService.getById(coursePost.getStudentId());
            WechatUser wechatUser = wechatUserService.getByOpenId(student.getOpenId());

            StudentResp studentResp = new StudentResp();
            studentResp.setId(student.getId());
            studentResp.setUsername(wechatUser.getNickName());
            studentResp.setMobile(student.getMobile());
            studentResp.setType(student.getType());
            studentResp.setGrade(student.getGrade());
            studentResp.setStatus(student.getStatus());
            studentResp.setAvatarUrl(wechatUser.getAvatarUrl());
            studentResp.setCreateTime(student.getCreateTime());
            studentResp.setUpdateTime(student.getUpdateTime());

            CoursePostResp coursePostResp = new CoursePostResp();
            coursePostResp.setId(coursePost.getId());
            coursePostResp.setStudent(studentResp);
            coursePostResp.setDemand(courseService.getById(coursePost.getDemand()));
            coursePostResp.setSupply(courseService.getById(coursePost.getSupply()));
            coursePostResp.setStatus(coursePost.getStatus());
            coursePostResp.setMobileSwitch(coursePost.getMobileSwitch());
            coursePostResp.setWechat(coursePost.getWechat());
            coursePostResp.setMessage(coursePost.getMessage());
            coursePostResp.setPv(pv);
            coursePostResp.setCreateTime(coursePost.getCreateTime());
            coursePostResp.setUpdateTime(coursePost.getUpdateTime());
            coursePostResp.setHasViewedContact(
                    coursePostService.hasViewedPostContact(currentStudent.getId(), id));
            return new ResponseEntity<>(coursePostResp, HttpStatus.OK);
        }
        throw new CoursePostNotFoundException();
    }

    @ApiOperation(value = "Create New Course Post")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "", method = RequestMethod.POST)
    ResponseEntity createNewCoursePost(@RequestBody AddCoursePostDto addCoursePostDto) {
        WechatUser wechatUser = wechatUtils.requireWechatUser();
        Student student = studentService.getByWechatUserId(wechatUser.getId());
        addCoursePostDto.setStudentId(student.getId());
        validCoursePost(addCoursePostDto);
        CoursePost coursePost = CoursePost.getByAddCoursePostDto(addCoursePostDto);
        coursePost.setMobile(student.getMobile());
        coursePostService.add(coursePost);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @ApiOperation(value = "Update Course Post")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity updateCoursePost(@PathVariable("id") long id,
                                    @RequestBody UpdateCoursePostDto updateCoursePostDto) {
        WechatUser wechatUser = wechatUtils.requireWechatUser();
        Student student = studentService.getByWechatUserId(wechatUser.getId());
        CoursePost coursePost = coursePostService.getById(id);
        if (coursePost != null) {
            if (coursePost.getStudentId().equals(student.getId())) {
                updateCoursePostDto.setId(id);
                coursePostService.update(updateCoursePostDto);
                return new ResponseEntity<>("OK", HttpStatus.OK);
            }
            throw new UpdateCoursePostException("Cannot update other user's post!");
        }
        throw new CoursePostNotFoundException();
    }

    @ApiOperation(value = "Update Course Post")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/viewcount", method = RequestMethod.GET)
    ResponseEntity getRemainingViewContactCount() {
        WechatUser wechatUser = wechatUtils.requireWechatUser();
        Student student = studentService.getByWechatUserId(wechatUser.getId());
        Integer remainingViewCount = studentService.getRemainingViewContactCount(student.getId());
        Map<String, Integer> resp = Maps.newHashMap();
        resp.put("remainingViewCount", remainingViewCount);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @ApiOperation(value = "Update Course Post")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/viewcount", method = RequestMethod.PUT)
    ResponseEntity viewCoursePostContact(@RequestBody UpdateCoursePostViewCountDto updateCoursePostViewCountDto) {
        WechatUser wechatUser = wechatUtils.requireWechatUser();
        Student student = studentService.getByWechatUserId(wechatUser.getId());
        coursePostService.viewPostContact(student.getId(), updateCoursePostViewCountDto.getId());
        studentService.incrViewContactCount(student.getId());
        return new ResponseEntity<>(Maps.newHashMap(), HttpStatus.OK);
    }
}
