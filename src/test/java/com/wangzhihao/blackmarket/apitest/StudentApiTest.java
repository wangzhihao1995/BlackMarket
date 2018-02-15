package com.wangzhihao.blackmarket.apitest;

import com.wangzhihao.blackmarket.data.MockData;
import com.wangzhihao.blackmarket.domain.WechatSession;
import com.wangzhihao.blackmarket.domain.WechatUser;
import com.wangzhihao.blackmarket.dto.AddStudentDto;
import com.wangzhihao.blackmarket.exception.BlackMarketException;
import com.wangzhihao.blackmarket.service.StudentService;
import com.wangzhihao.blackmarket.service.WechatSessionService;
import com.wangzhihao.blackmarket.service.WechatUserService;
import com.wangzhihao.blackmarket.utils.DtoUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Description
 * <p>
 * </p>
 * DATE 10/02/2018.
 *
 * @author Wang Zhihao.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql("/dev.sql")
public class StudentApiTest {

    private static final String STUDENT_API_PREFIX = "/api/student";
    private static final String X_USER_SESSION_KEY = "X-User-Session-Key";

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private StudentService studentService;

    @Autowired
    private WechatSessionService wechatSessionService;

    @Autowired
    private WechatUserService wechatUserService;

    @Before
    public void init() {
        WechatUser wechatUser = MockData.mockWechatUser();
        assertEquals(MockData.mockOpenID(), wechatUser.getOpenId());
        wechatSessionService.add(MockData.mockOpenID(), MockData.mockSessionKey());
        wechatUserService.add(wechatUser);
    }

    @Test
    public void studentApiTest() {
        try {
            RequestBuilder request;

            WechatSession wechatSession = wechatSessionService.getByOpenId(MockData.mockOpenID());
            WechatUser wechatUser = wechatUserService.getByOpenId(wechatSession.getOpenId());
            String thirdSessionKey = wechatSession.getThirdSessionKey();

            AddStudentDto addStudentDto = new AddStudentDto();

            addStudentDto.setOpenId(wechatUser.getOpenId());
            addStudentDto.setWechatUserId(wechatUser.getId());
            addStudentDto.setName(wechatUser.getNickname());
            addStudentDto.setMobile("15600000000");
            addStudentDto.setStatus(1);
            addStudentDto.setType(1);
            addStudentDto.setGrade(2017);

            request = post(STUDENT_API_PREFIX).header(X_USER_SESSION_KEY, thirdSessionKey)
                    .content(DtoUtils.toJsonString(addStudentDto))
                    .contentType(MediaType.APPLICATION_JSON);
            mockMvc.perform(request).andDo(print()).andExpect(status().isCreated());


            request = get(STUDENT_API_PREFIX).header(X_USER_SESSION_KEY, thirdSessionKey);
            mockMvc.perform(request).andDo(print()).andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.wechatUserId").value(wechatUser.getId()))
                    .andExpect(jsonPath("$.openId").value(wechatUser.getOpenId()));

            request = get(STUDENT_API_PREFIX + "/1").header(X_USER_SESSION_KEY, thirdSessionKey);
            mockMvc.perform(request).andDo(print()).andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.wechatUserId").value(wechatUser.getId()))
                    .andExpect(jsonPath("$.openId").value(wechatUser.getOpenId()));


        } catch (Exception e) {
            throw new BlackMarketException(e);
        }
    }
}
