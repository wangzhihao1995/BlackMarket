package com.wangzhihao.blackmarket.service;

import com.wangzhihao.blackmarket.domain.WechatSession;
import com.wangzhihao.blackmarket.dto.UpdateWechatSessionDto;
import org.springframework.stereotype.Service;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/29.
 *
 * @author Wang Zhihao.
 */
@Service
public interface WechatSessionService {

    WechatSession add(String openId, String sessionKey);

    WechatSession getById(Long id);

    WechatSession getByThirdSessionKey(String thirdSessionKey);

    WechatSession getByOpenId(String openId);

    void update(UpdateWechatSessionDto updateWechatSessionDto);

}
