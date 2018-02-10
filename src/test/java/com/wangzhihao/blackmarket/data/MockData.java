package com.wangzhihao.blackmarket.data;

import com.wangzhihao.blackmarket.domain.Course;
import com.wangzhihao.blackmarket.domain.File;
import com.wangzhihao.blackmarket.domain.Student;
import com.wangzhihao.blackmarket.domain.WechatUser;
import com.wangzhihao.blackmarket.dto.UpdateWechatUserDto;
import com.wangzhihao.blackmarket.enums.FileStautsEnum;
import com.wangzhihao.blackmarket.enums.FileTypeEnum;
import com.wangzhihao.blackmarket.enums.GenderEnum;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/12/1.
 *
 * @author Wang Zhihao.
 */
public class MockData {

    private static final String OPENID = "o-irt0PDNL3neAUoa3HiKDnIxd_d";
    private static final String SESSION_KEY = "Jvx8/G/0SAFaj+LlLP6d+w==";

    private MockData() {
        throw new IllegalAccessError("Utility class");
    }

    public static WechatUser mockWechatUser() {
        WechatUser wechatUser = new WechatUser();
        UpdateWechatUserDto updateWechatUserDto = new UpdateWechatUserDto();
        updateWechatUserDto.setOpenId(OPENID);
        updateWechatUserDto.setNickname("Mew_Wzh");
        updateWechatUserDto.setGender(GenderEnum.MALE.getValue());
        updateWechatUserDto.setLanguage("en");
        updateWechatUserDto.setCity("Beijing");
        updateWechatUserDto.setProvince("Beijing");
        updateWechatUserDto.setCountry("China");
        updateWechatUserDto.setAvatarUrl("https://pkublackmarket.cn/static/img/app-qrcode.jpg");
        wechatUser.setByUpdateWechatUserDto(updateWechatUserDto);
        return wechatUser;
    }

    public static String mockOpenID() {
        return OPENID;
    }

    public static String mockSessionKey() {
        return SESSION_KEY;
    }

    public static Course mockCourse() {
        Course course = new Course();
        course.setName("CNN");
        course.setTeacher("Andrew");
        course.setCredit(4);
        course.setYear(2018L);
        course.setSemester("spring");
        return course;
    }

    public static Student mockStudent() {
        Student student = new Student();
        student.setWechatUserId(1L);
        student.setName("Mew");
        student.setMobile("13000000000");
        student.setOpenId(OPENID);
        student.setType(1);
        student.setGrade("2017");
        student.setStatus(1);
        return student;
    }

    public static File mockImage() {
        File file = new File();
        file.setType(FileTypeEnum.IMAGE.getValue());
        file.setUploaderId(1L);
        file.setStatus(FileStautsEnum.NOT_READY.getValue());
        file.setKey("demo.jgp");
        file.setUrl("https://blackmarket.com/file/demo.jgp");
        return file;
    }
}
