package com.wangzhihao.blackmarket.service.impl;

import com.wangzhihao.blackmarket.domain.Course;
import com.wangzhihao.blackmarket.mapper.CourseMapper;
import com.wangzhihao.blackmarket.service.CourseService;
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
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseMapper courseMapper;

    @Override
    public Course getById(Long id) {
        return courseMapper.findCourseById(id);
    }
}
