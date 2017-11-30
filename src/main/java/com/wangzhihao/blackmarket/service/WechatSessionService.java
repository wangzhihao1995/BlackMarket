package com.wangzhihao.blackmarket.service;

import com.wangzhihao.blackmarket.domain.WechatSession;
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
@FunctionalInterface
public interface WechatSessionService {
    WechatSession getById(Long id);
}
