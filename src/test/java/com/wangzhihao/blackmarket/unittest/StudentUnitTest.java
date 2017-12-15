package com.wangzhihao.blackmarket.unittest;

import com.wangzhihao.blackmarket.data.MockDoamin;
import com.wangzhihao.blackmarket.domain.Student;
import com.wangzhihao.blackmarket.domain.WechatUser;
import com.wangzhihao.blackmarket.dto.UpdateStudentDto;
import com.wangzhihao.blackmarket.service.StudentService;
import com.wangzhihao.blackmarket.service.WechatUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentUnitTest {

    @Autowired
    StudentService studentService;

    @Autowired
    WechatUserService wechatUserService;

    @Test
    @Sql("/dev.sql")
    public void testStudentService() {

        WechatUser mockWechatUser = MockDoamin.mockWechatUser();
        wechatUserService.add(mockWechatUser);

        Student mockStudent = MockDoamin.mockStudent();
        mockStudent.setId(mockWechatUser.getId());
        studentService.add(mockStudent);
        assertNotNull(mockStudent.getId());

        Student student = studentService.getById(mockStudent.getId());
        assertEquals(student.getOpenId(), mockStudent.getOpenId());
        UpdateStudentDto updateStudentDto = new UpdateStudentDto();
        updateStudentDto.setId(mockWechatUser.getId());
        updateStudentDto.setMobile("15600000000");
        studentService.updateStudent(updateStudentDto);
        student = studentService.getById(mockStudent.getId());
        assertEquals(student.getMobile(), "15600000000");
    }
}
