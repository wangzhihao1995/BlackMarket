package com.wangzhihao.blackmarket.mapper;

import com.wangzhihao.blackmarket.domain.CoursePost;
import com.wangzhihao.blackmarket.dto.GetCoursePostListDto;
import com.wangzhihao.blackmarket.dto.UpdateCoursePostDto;
import org.apache.ibatis.annotations.Mapper;

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
public interface CoursePostMapper {
    void insert(CoursePost coursePost);

    CoursePost findCoursePostById(Long id);

    List<CoursePost> findCoursePosts(GetCoursePostListDto getCoursePostListDto);

    void update(UpdateCoursePostDto updateCoursePostDto);

    void incrPv(Long id);
}
