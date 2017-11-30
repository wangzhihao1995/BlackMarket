package com.wangzhihao.blackmarket.service.impl;

import com.wangzhihao.blackmarket.domain.Student;
import com.wangzhihao.blackmarket.mapper.StudentMapper;
import com.wangzhihao.blackmarket.service.StudentService;
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
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public Student getById(Long id) {
        return studentMapper.findStudentById(id);
    }
}