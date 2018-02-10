package com.wangzhihao.blackmarket.unittest;

import com.wangzhihao.blackmarket.data.MockData;
import com.wangzhihao.blackmarket.domain.WechatUser;
import com.wangzhihao.blackmarket.dto.UpdateWechatUserDto;
import com.wangzhihao.blackmarket.enums.GenderEnum;
import com.wangzhihao.blackmarket.service.WechatUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

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
public class WechatUserUnitTest {

    @Autowired
    WechatUserService wechatUserService;

    @Test
    @Sql("/dev.sql")
    public void testWechatUserService() {
        WechatUser mockWechatUser = MockData.mockWechatUser();
        assertNull(mockWechatUser.getId());
        wechatUserService.add(mockWechatUser);
        assertNotNull(mockWechatUser.getId());

        WechatUser wechatUser = wechatUserService.getById(mockWechatUser.getId());
        assertEquals(wechatUser.getId(), mockWechatUser.getId());
        assertEquals(wechatUser.getOpenId(), mockWechatUser.getOpenId());

        wechatUser = wechatUserService.getByOpenId(mockWechatUser.getOpenId());
        assertEquals(wechatUser.getId(), mockWechatUser.getId());
        assertEquals(wechatUser.getOpenId(), mockWechatUser.getOpenId());

        UpdateWechatUserDto updateWechatUserDto = new UpdateWechatUserDto();
        updateWechatUserDto.setOpenId(mockWechatUser.getOpenId());
        updateWechatUserDto.setGender(GenderEnum.UNKNOWN.getValue());
        updateWechatUserDto.setLanguage(mockWechatUser.getLanguage());
        updateWechatUserDto.setCity(mockWechatUser.getCity());
        updateWechatUserDto.setProvince(mockWechatUser.getProvince());
        updateWechatUserDto.setAvatarUrl(mockWechatUser.getAvatarUrl());
        wechatUserService.updateWechatUser(updateWechatUserDto);

        wechatUser = wechatUserService.getByOpenId(mockWechatUser.getOpenId());
        assertEquals(wechatUser.getId(), mockWechatUser.getId());
        assertEquals(wechatUser.getOpenId(), mockWechatUser.getOpenId());
        assertEquals(wechatUser.getGender(), GenderEnum.UNKNOWN.getValue());

        updateWechatUserDto.setOpenId("test" + updateWechatUserDto.getOpenId());
        wechatUserService.updateWechatUser(updateWechatUserDto);
        wechatUser = wechatUserService.getByOpenId(updateWechatUserDto.getOpenId());
        assertNotEquals(wechatUser.getId(), mockWechatUser.getId());
        assertNotEquals(wechatUser.getOpenId(), mockWechatUser.getOpenId());
    }
}
