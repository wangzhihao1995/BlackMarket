package com.wangzhihao.blackmarket.service.impl;

import com.wangzhihao.blackmarket.domain.WechatSession;
import com.wangzhihao.blackmarket.mapper.WechatSessionMapper;
import com.wangzhihao.blackmarket.service.WechatSessionService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class WechatSessionServiceImpl implements WechatSessionService {

    @Autowired
    WechatSessionMapper wechatSessionMapper;

    @Override
    public WechatSession getById(Long id) {
        return wechatSessionMapper.findWechatSessionById(id);
    }
}
