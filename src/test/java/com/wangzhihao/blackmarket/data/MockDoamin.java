package com.wangzhihao.blackmarket.data;

import com.wangzhihao.blackmarket.domain.*;
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
public class MockDoamin {

    public static final String OPENID = "o-irt0PDNL3neAUoa3HiKDnIxd_d";
    public static final String SESSION_KEY = "Jvx8/G/0SAFaj+LlLP6d+w==";
    public static final String THIRD_SESSION_KEY = "c27bd9dd5d3f41388b2658c8041d01ed";

    private MockDoamin() {
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

    public static WechatSession mockWechatSession() {
        WechatSession wechatSession = new WechatSession();
        wechatSession.setOpenId(OPENID);
        wechatSession.setSessionKey(SESSION_KEY);
        wechatSession.setThirdSessionKey(THIRD_SESSION_KEY);
        return wechatSession;
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
        student.setName("Mew");
        student.setMobile("13000000000");
        student.setOpenId(mockWechatSession().getOpenId());
        student.setType(1L);
        student.setGrade("2017");
        student.setStatus(1L);
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
