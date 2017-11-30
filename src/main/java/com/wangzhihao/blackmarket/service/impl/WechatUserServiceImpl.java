package com.wangzhihao.blackmarket.service.impl;

import com.wangzhihao.blackmarket.domain.WechatUser;
import com.wangzhihao.blackmarket.dto.UpdateWechatUserDto;
import com.wangzhihao.blackmarket.mapper.WechatUserMapper;
import com.wangzhihao.blackmarket.service.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class WechatUserServiceImpl implements WechatUserService {

    @Autowired
    WechatUserMapper wechatUserMapper;

    @Override
    public void add(WechatUser wechatUser) {
        wechatUserMapper.insert(wechatUser);
    }

    @Override
    public WechatUser getById(Long id) {
        return wechatUserMapper.findWechatUserById(id);
    }

    @Override
    public WechatUser getByOpenId(String openId) {
        return wechatUserMapper.findWechatUserByOpenId(openId);
    }

    @Override
    public void updateWechatUser(UpdateWechatUserDto updateWechatUserDto) {
        WechatUser wechatUser = this.getByOpenId(updateWechatUserDto.getOpenId());
        if (wechatUser != null) {
            wechatUserMapper.update(updateWechatUserDto);
        } else {
            wechatUser = new WechatUser();
            wechatUser.setByUpdateWechatUserDto(updateWechatUserDto);
            this.add(wechatUser);
        }
    }


}