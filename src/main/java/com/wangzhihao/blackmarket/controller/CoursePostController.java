package com.wangzhihao.blackmarket.controller;

import com.wangzhihao.blackmarket.domain.CoursePost;
import com.wangzhihao.blackmarket.domain.Student;
import com.wangzhihao.blackmarket.domain.WechatUser;
import com.wangzhihao.blackmarket.dto.AddCoursePostDto;
import com.wangzhihao.blackmarket.dto.GetCoursePostListDto;
import com.wangzhihao.blackmarket.dto.UpdateCoursePostDto;
import com.wangzhihao.blackmarket.enums.CoursePostStautsEnumBlackMarket;
import com.wangzhihao.blackmarket.exception.AddCoursePostException;
import com.wangzhihao.blackmarket.exception.CoursePostNotFoundException;
import com.wangzhihao.blackmarket.exception.UpdateCoursePostException;
import com.wangzhihao.blackmarket.service.CoursePostService;
import com.wangzhihao.blackmarket.service.StudentService;
import com.wangzhihao.blackmarket.utils.WechatUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    StudentService studentService;

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

    @ApiOperation(value = "Get Course Post List")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseEntity getCoursePostList(GetCoursePostListDto getCoursePostListDto) {
        return new ResponseEntity<>(coursePostService.getCoursePostList(getCoursePostListDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Get Course Post By ID")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity getCoursePostById(@PathVariable("id") long id) {
        wechatUtils.requireWechatUser();
        CoursePost coursePost = coursePostService.getById(id);
        if (coursePost != null) {
            Long pv = coursePostService.incrPv(coursePost);
            coursePost.setPv(pv);
            return new ResponseEntity<>(coursePost, HttpStatus.OK);
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
}
