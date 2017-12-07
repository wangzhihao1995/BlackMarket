package com.wangzhihao.blackmarket.service;

import com.wangzhihao.blackmarket.domain.Course;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/29.
 *
 * @author Wang Zhihao.
 */
@Service
public interface CourseService {
    Course getById(Long id);

    List<Course> getListByYearAndSemester(Long year, String semester);
}
