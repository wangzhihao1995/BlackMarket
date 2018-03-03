package com.wangzhihao.blackmarket.service;

import com.wangzhihao.blackmarket.domain.WechatUser;
import com.wangzhihao.blackmarket.dto.UpdateWechatUserDto;
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
public interface WechatUserService {

    void add(WechatUser wechatUser);

    WechatUser getById(Long id);

    WechatUser getByOpenId(String openId);

    void updateWechatUser(UpdateWechatUserDto updateWechatUserDto);
}
