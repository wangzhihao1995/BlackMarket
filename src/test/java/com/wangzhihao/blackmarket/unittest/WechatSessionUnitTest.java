package com.wangzhihao.blackmarket.unittest;

import com.wangzhihao.blackmarket.data.MockDoamin;
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
        WechatSession mockWechatSession = MockDoamin.mockWechatSession();
        WechatSession wechatSession = wechatSessionService.add(
                mockWechatSession.getOpenId(), mockWechatSession.getSessionKey());
        assertEquals(wechatSession.getOpenId(), mockWechatSession.getOpenId());
        assertEquals(wechatSession.getSessionKey(), mockWechatSession.getSessionKey());
        assertNotEquals(wechatSession.getThirdSessionKey(), mockWechatSession.getThirdSessionKey());

        WechatSession anotherSession = wechatSessionService.add(
                mockWechatSession.getOpenId(), mockWechatSession.getSessionKey());
        assertEquals(anotherSession.getOpenId(), mockWechatSession.getOpenId());
        assertEquals(anotherSession.getSessionKey(), mockWechatSession.getSessionKey());
        assertNotEquals(wechatSession.getThirdSessionKey(), anotherSession.getThirdSessionKey());

        wechatSession = wechatSessionService.getByThirdSessionKey(anotherSession.getThirdSessionKey());
        assertEquals(wechatSession.getOpenId(), anotherSession.getOpenId());
        assertEquals(wechatSession.getSessionKey(), anotherSession.getSessionKey());
        assertEquals(wechatSession.getThirdSessionKey(), anotherSession.getThirdSessionKey());
    }
}
