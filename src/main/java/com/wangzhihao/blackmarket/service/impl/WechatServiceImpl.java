package com.wangzhihao.blackmarket.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wangzhihao.blackmarket.exception.Jscode2SessionException;
import com.wangzhihao.blackmarket.service.WechatService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/29.
 *
 * @author Wang Zhihao.
 */
@Service
public class WechatServiceImpl implements WechatService {

    private static final String JSCODE2SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session";
    private static final String DEFAULT_GRANT_TYPE = "authorization_code";

    @Value("${blackmarket.wechat.miniapp.appid}")
    public String appId;

    @Value("${blackmarket.wechat.miniapp.appsecret}")
    public String appSecret;

    @Override
    public JSONObject jscode2session(String code) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(JSCODE2SESSION_URL)
                .queryParam("appid", appId)
                .queryParam("secret", appSecret)
                .queryParam("js_code", code)
                .queryParam("grant_type", DEFAULT_GRANT_TYPE);
        JSONObject res = JSON.parseObject(restTemplate.getForObject(builder.toUriString(), String.class));
        if (res.get("errcode") != null) {
            throw new Jscode2SessionException(String.format("[%s]%s", res.get("errcode"), res.get("errmsg")));
        }
        return res;
    }
}
