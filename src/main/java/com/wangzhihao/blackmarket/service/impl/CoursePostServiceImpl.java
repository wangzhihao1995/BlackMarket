package com.wangzhihao.blackmarket.service.impl;

import com.wangzhihao.blackmarket.domain.CoursePost;
import com.wangzhihao.blackmarket.mapper.CoursePostMapper;
import com.wangzhihao.blackmarket.service.CoursePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public CoursePost getById(Long id) {
        return coursePostMapper.findCoursePostById(id);
    }
}
