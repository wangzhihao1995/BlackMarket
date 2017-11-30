package com.wangzhihao.blackmarket.mapper;

import com.wangzhihao.blackmarket.domain.CoursePost;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/30.
 *
 * @author Wang Zhihao.
 */
@Mapper
@FunctionalInterface
public interface CoursePostMapper {
    CoursePost findCoursePostById(Long id);
}
