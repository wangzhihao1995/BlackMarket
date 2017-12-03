package com.wangzhihao.blackmarket.data;

import com.wangzhihao.blackmarket.domain.WechatSession;
import com.wangzhihao.blackmarket.domain.WechatUser;
import com.wangzhihao.blackmarket.dto.UpdateWechatUserDto;
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

    public static final String OPENID = "o-irt0KDKL3neAUoa3HiKDnIxc_c";
    public static final String SESSION_KEY = "AqZ0/be6x/Dnk3WTIJ+ywQ==";
    public static final String THIRD_SESSION_KEY = "6de985a38e604485bb7fd63b5a2a1622";

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

}