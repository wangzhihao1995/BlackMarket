package com.wangzhihao.blackmarket.mapper;

import com.wangzhihao.blackmarket.domain.CourseSchedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/30.
 *
 * @author Wang Zhihao.
 */
@Mapper
public interface CourseScheduleMapper {

    void insert(CourseSchedule courseSchedule);

    CourseSchedule findById(@Param("id") Long id);

    List<CourseSchedule> findByCourseId(@Param("courseId") Long courseId);

    List<CourseSchedule> findByCourseIds(@Param("courseIds") Set<Long> courseIds);

}
