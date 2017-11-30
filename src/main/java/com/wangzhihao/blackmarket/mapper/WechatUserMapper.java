package com.wangzhihao.blackmarket.mapper;

import com.wangzhihao.blackmarket.domain.WechatUser;
import com.wangzhihao.blackmarket.dto.UpdateWechatUserDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/30.
 *
 * @author Wang Zhihao.
 */
@Mapper
public interface WechatUserMapper {

    void insert(WechatUser wechatUser);

    WechatUser findWechatUserById(Long id);

    WechatUser findWechatUserByOpenId(String openId);

    void update(UpdateWechatUserDto updateWechatUserDto);
}
