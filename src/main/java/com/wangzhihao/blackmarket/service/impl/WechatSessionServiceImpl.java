package com.wangzhihao.blackmarket.service.impl;

import com.wangzhihao.blackmarket.domain.WechatSession;
import com.wangzhihao.blackmarket.dto.UpdateWechatSessionDto;
import com.wangzhihao.blackmarket.mapper.WechatSessionMapper;
import com.wangzhihao.blackmarket.service.WechatSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/29.
 *
 * @author Wang Zhihao.
 */
@Service
public class WechatSessionServiceImpl implements WechatSessionService {

    @Autowired
    WechatSessionMapper wechatSessionMapper;

    @Override
    public WechatSession add(String openId, String sessionKey) {
        WechatSession wechatSession = getByOpenId(openId);
        String thirdSessionKey = UUID.randomUUID().toString();
        if (wechatSession == null) {
            wechatSession = new WechatSession();
            wechatSession.setOpenId(openId);
            wechatSession.setSessionKey(sessionKey);
            wechatSession.setThirdSessionKey(thirdSessionKey);
            wechatSessionMapper.insert(wechatSession);
            return wechatSession;
        }
        UpdateWechatSessionDto updateWechatSessionDto = new UpdateWechatSessionDto();
        updateWechatSessionDto.setId(wechatSession.getId());
        updateWechatSessionDto.setSessionKey(sessionKey);
        updateWechatSessionDto.setThirdSessionKey(thirdSessionKey);
        update(updateWechatSessionDto);
        return getById(updateWechatSessionDto.getId());
    }

    @Override
    public WechatSession getById(Long id) {
        return wechatSessionMapper.findById(id);
    }

    @Override
    public WechatSession getByThirdSessionKey(String thirdSessionKey) {
        return wechatSessionMapper.findByThirdSessionKey(thirdSessionKey);
    }

    @Override
    public WechatSession getByOpenId(String openId) {
        return wechatSessionMapper.findByOpenId(openId);
    }

    @Override
    public void update(UpdateWechatSessionDto updateWechatSessionDto) {
        wechatSessionMapper.update(updateWechatSessionDto);
    }
}
