package com.wangzhihao.blackmarket.service;

import com.wangzhihao.blackmarket.domain.Student;
import com.wangzhihao.blackmarket.dto.UpdateStudentDto;
import org.springframework.stereotype.Service;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/29.
 *
 * @author Wang Zhihao.
 */
@Service
public interface StudentService {
    void add(Student student);

    Student getById(Long id);

    Student getByWechatUserId(Long wechatUserId);

    Student getByOpenId(String openId);

    void updateStudent(UpdateStudentDto updateStudentDto);
}
