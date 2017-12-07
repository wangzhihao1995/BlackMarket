package com.wangzhihao.blackmarket.mapper;

import com.wangzhihao.blackmarket.domain.Student;
import com.wangzhihao.blackmarket.dto.UpdateStudentDto;
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
public interface StudentMapper {
    void insert(Student student);

    Student findStudentById(Long id);

    Student findStudentByOpenId(String openId);

    void update(UpdateStudentDto updateStudentDto);
}
