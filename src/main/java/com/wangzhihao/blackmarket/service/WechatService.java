package com.wangzhihao.blackmarket.service;

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
public interface WechatService {
    String jscode2session(String code);
}
