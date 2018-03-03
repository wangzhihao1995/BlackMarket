package com.wangzhihao.blackmarket.service.impl;

import com.wangzhihao.blackmarket.domain.Student;
import com.wangzhihao.blackmarket.dto.UpdateStudentDto;
import com.wangzhihao.blackmarket.exception.StudentNotFoundException;
import com.wangzhihao.blackmarket.mapper.CoursePostMapper;
import com.wangzhihao.blackmarket.mapper.StudentMapper;
import com.wangzhihao.blackmarket.service.StudentService;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

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
public class StudentServiceImpl implements StudentService {

    private static final String STUDENT_VIEW_POST_CONTACT_COUNT_CACHE_KEY = "student:%s:view:contact:count";
    private static final Integer TOTAL_AVAILABLE_VIEW_COUNT = 15;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void add(Student student) {
        studentMapper.insert(student);
    }

    @Override
    public Student getById(Long id) {
        return studentMapper.findStudentById(id);
    }

    @Override
    public Student getByWechatUserId(Long wechatUserId) {
        Student student = studentMapper.findStudentByWechatUserId(wechatUserId);
        if (student != null) {
            return student;
        }
        throw new StudentNotFoundException();
    }

    @Override
    public Student getByOpenId(String openId) {
        return studentMapper.findStudentByOpenId(openId);
    }

    @Override
    public void updateStudent(UpdateStudentDto updateStudentDto) {
        studentMapper.update(updateStudentDto);
    }

    @Override
    public void incrViewContactCount(Long studentId) {
        String key = String.format(STUDENT_VIEW_POST_CONTACT_COUNT_CACHE_KEY, studentId);
        String val = stringRedisTemplate.opsForValue().get(key);
        stringRedisTemplate.opsForValue().increment(key, 1);
        if (val != null) {
            stringRedisTemplate.expire(key, 1, TimeUnit.DAYS);
        }
    }

    @Override
    public Integer getRemainingViewContactCount(Long studentId) {
        Integer viewCount = 0;
        String key = String.format(STUDENT_VIEW_POST_CONTACT_COUNT_CACHE_KEY, studentId);
        String val = stringRedisTemplate.opsForValue().get(key);
        if (val != null) {
            viewCount = Integer.parseInt(val);
        }
        return TOTAL_AVAILABLE_VIEW_COUNT - viewCount;
    }
}
