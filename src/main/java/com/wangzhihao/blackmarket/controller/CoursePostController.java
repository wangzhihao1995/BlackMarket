package com.wangzhihao.blackmarket.controller;

import com.wangzhihao.blackmarket.domain.CoursePost;
import com.wangzhihao.blackmarket.domain.WechatUser;
import com.wangzhihao.blackmarket.dto.AddCoursePostDto;
import com.wangzhihao.blackmarket.dto.GetCoursePostListDto;
import com.wangzhihao.blackmarket.dto.UpdateCoursePostDto;
import com.wangzhihao.blackmarket.exception.CoursePostNotFoundException;
import com.wangzhihao.blackmarket.exception.UpdateCoursePostException;
import com.wangzhihao.blackmarket.service.CoursePostService;
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
@RequestMapping(value = "/api/v1/course/post")
public class CoursePostController {

    @Autowired
    CoursePostService coursePostService;

    @Autowired
    WechatUtils wechatUtils;

    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseEntity getCoursePostList(GetCoursePostListDto getCoursePostListDto) {
        return new ResponseEntity<>(coursePostService.getCoursePostList(getCoursePostListDto), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity getCoursePostById(@PathVariable("id") long id) {
        CoursePost coursePost = coursePostService.getById(id);
        if (coursePost != null) {
            coursePostService.incrPv(coursePost.getId());
            coursePost.setPv(coursePost.getPv() + 1);
            return new ResponseEntity<>(coursePost, HttpStatus.OK);
        }
        throw new CoursePostNotFoundException();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    ResponseEntity createNewCoursePost(@RequestBody AddCoursePostDto addCoursePostDto) {
        WechatUser wechatUser = wechatUtils.requireWechatUser();
        addCoursePostDto.setStudentId(wechatUser.getId());
        CoursePost coursePost = new CoursePost();
        coursePost.setByAddCoursePostDto(addCoursePostDto);
        coursePostService.add(coursePost);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity updateCoursePost(@PathVariable("id") long id,
                                    @RequestBody UpdateCoursePostDto updateCoursePostDto) {
        WechatUser wechatUser = wechatUtils.requireWechatUser();
        CoursePost coursePost = coursePostService.getById(id);
        if (coursePost != null) {
            if (coursePost.getStudentId().equals(wechatUser.getId())) {
                coursePostService.update(updateCoursePostDto);
                return new ResponseEntity<>("OK", HttpStatus.OK);
            }
            throw new UpdateCoursePostException("Cannot update other user's post!");
        }
        throw new CoursePostNotFoundException();
    }
}
