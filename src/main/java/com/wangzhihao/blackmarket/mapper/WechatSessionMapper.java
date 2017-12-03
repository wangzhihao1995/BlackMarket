package com.wangzhihao.blackmarket.mapper;

import com.wangzhihao.blackmarket.domain.WechatSession;
import com.wangzhihao.blackmarket.dto.UpdateWechatSessionDto;
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
public interface WechatSessionMapper {

    void insert(WechatSession wechatSession);

    WechatSession findById(Long id);

    WechatSession findByThirdSessionKey(String thirdSessionKey);

    WechatSession findByOpenId(String openId);

    void update(UpdateWechatSessionDto updateWechatSessionDto);

}
