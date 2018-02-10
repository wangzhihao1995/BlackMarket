package com.wangzhihao.blackmarket.mapper;

import com.wangzhihao.blackmarket.domain.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/30.
 *
 * @author Wang Zhihao.
 */
@Mapper
public interface CourseMapper {

    void insert(Course course);

    Course findCourseById(Long id);

    List<Course> findByYearAndSemester(@Param("year") Integer year, @Param("semester") String semester);
}
