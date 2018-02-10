package com.wangzhihao.blackmarket.unittest;

import com.wangzhihao.blackmarket.data.MockData;
import com.wangzhihao.blackmarket.domain.WechatSession;
import com.wangzhihao.blackmarket.service.WechatSessionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
public class WechatSessionUnitTest {

    @Autowired
    WechatSessionService wechatSessionService;

    @Test
    @Sql("/dev.sql")
    public void testWechatSessionService() {
        WechatSession wechatSession = wechatSessionService.add(
                MockData.mockOpenID(), MockData.mockSessionKey());
        assertEquals(wechatSession.getOpenId(), MockData.mockOpenID());
        assertEquals(wechatSession.getSessionKey(), MockData.mockSessionKey());

        WechatSession anotherSession = wechatSessionService.add(
                MockData.mockOpenID(), MockData.mockSessionKey());
        assertEquals(anotherSession.getOpenId(), MockData.mockOpenID());
        assertEquals(anotherSession.getSessionKey(), MockData.mockSessionKey());
        assertNotEquals(wechatSession.getThirdSessionKey(), anotherSession.getThirdSessionKey());

        wechatSession = wechatSessionService.getByThirdSessionKey(anotherSession.getThirdSessionKey());
        assertEquals(wechatSession.getOpenId(), anotherSession.getOpenId());
        assertEquals(wechatSession.getSessionKey(), anotherSession.getSessionKey());
        assertEquals(wechatSession.getThirdSessionKey(), anotherSession.getThirdSessionKey());
    }
}
