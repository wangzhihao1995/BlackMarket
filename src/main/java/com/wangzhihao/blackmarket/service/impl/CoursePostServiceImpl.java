package com.wangzhihao.blackmarket.service.impl;

import com.wangzhihao.blackmarket.domain.CoursePost;
import com.wangzhihao.blackmarket.dto.GetCoursePostListDto;
import com.wangzhihao.blackmarket.dto.UpdateCoursePostDto;
import com.wangzhihao.blackmarket.mapper.CoursePostMapper;
import com.wangzhihao.blackmarket.service.CoursePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
public class CoursePostServiceImpl implements CoursePostService {

    private static final String COURSE_POST_PV_CACHE_KEY = "course:post:pv:id:%d";
    private static final String STUDENT_VIEWED_POST_CONTACT_CACHE_KEY = "student:%d:viewed:contact:course:post:%d";

    @Autowired
    CoursePostMapper coursePostMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
    public Long incrPv(CoursePost coursePost) {
        Long pv = coursePost.getPv() + 1;
        String key = String.format(COURSE_POST_PV_CACHE_KEY, coursePost.getId());
        String val = stringRedisTemplate.opsForValue().get(key);
        if (val != null) {
            pv = Long.parseLong(val) + 1;
        }
        stringRedisTemplate.opsForValue().set(key, pv.toString());
        stringRedisTemplate.expire(key, 14, TimeUnit.DAYS);
        if (pv % 7 == 0) {
            coursePostMapper.updatePv(coursePost.getId(), pv);
        }
        return pv;
    }

    @Override
    public Boolean hasViewedPostContact(Long studentId, Long postId) {
        String key = String.format(STUDENT_VIEWED_POST_CONTACT_CACHE_KEY, studentId, postId);
        String val = stringRedisTemplate.opsForValue().get(key);
        return val != null;
    }

    @Override
    public void viewPostContact(Long studentId, Long postId) {
        String key = String.format(STUDENT_VIEWED_POST_CONTACT_CACHE_KEY, studentId, postId);
        stringRedisTemplate.opsForValue().set(key, "");
        stringRedisTemplate.expire(key, 3, TimeUnit.DAYS);
    }
}
