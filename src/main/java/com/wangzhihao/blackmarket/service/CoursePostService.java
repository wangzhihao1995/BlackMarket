package com.wangzhihao.blackmarket.service;

import com.wangzhihao.blackmarket.domain.CoursePost;
import com.wangzhihao.blackmarket.dto.GetCoursePostListDto;
import com.wangzhihao.blackmarket.dto.UpdateCoursePostDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/30.
 *
 * @author Wang Zhihao.
 */
@Service
public interface CoursePostService {
    void add(CoursePost coursePost);

    CoursePost getById(Long id);

    List<CoursePost> getCoursePostList(GetCoursePostListDto getCoursePostListDto);

    void update(UpdateCoursePostDto updateCoursePostDto);

    Long incrPv(CoursePost coursePost);

    Boolean hasViewedPostContact(Long studentId, Long postId);

    void viewPostContact(Long studentId, Long postId);
}
