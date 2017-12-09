package com.wangzhihao.blackmarket.service.impl;

import com.wangzhihao.blackmarket.domain.CoursePost;
import com.wangzhihao.blackmarket.dto.GetCoursePostListDto;
import com.wangzhihao.blackmarket.dto.UpdateCoursePostDto;
import com.wangzhihao.blackmarket.mapper.CoursePostMapper;
import com.wangzhihao.blackmarket.service.CoursePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/30.
 *
 * @author Wang Zhihao.
 */
@Service
public class CoursePostServiceImpl implements CoursePostService {

    @Autowired
    CoursePostMapper coursePostMapper;

    @Override
    public void add(CoursePost coursePost) {
        coursePostMapper.insert(coursePost);
    }

    @Override
    public CoursePost getById(Long id) {
        return coursePostMapper.findCoursePostById(id);
    }

    @Override
    public List<CoursePost> getCoursePostList(GetCoursePostListDto getCoursePostListDto) {
        return coursePostMapper.findCoursePosts(getCoursePostListDto);
    }

    @Override
    public void update(UpdateCoursePostDto updateCoursePostDto) {
        coursePostMapper.update(updateCoursePostDto);
    }

    @Override
    public void incrPv(Long id) {
        coursePostMapper.incrPv(id);
    }
}
