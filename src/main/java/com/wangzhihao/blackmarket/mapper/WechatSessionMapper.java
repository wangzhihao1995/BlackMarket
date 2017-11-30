package com.wangzhihao.blackmarket.mapper;

import com.wangzhihao.blackmarket.domain.WechatSession;
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
@FunctionalInterface
public interface WechatSessionMapper {
    WechatSession findWechatSessionById(Long id);
}
