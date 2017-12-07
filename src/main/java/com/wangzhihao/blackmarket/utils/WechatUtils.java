package com.wangzhihao.blackmarket.utils;

import com.wangzhihao.blackmarket.domain.WechatSession;
import com.wangzhihao.blackmarket.domain.WechatUser;
import com.wangzhihao.blackmarket.exception.MissingSessionKeyException;
import com.wangzhihao.blackmarket.exception.WechatSessionNotFoundException;
import com.wangzhihao.blackmarket.exception.WechatUserNotFoundException;
import com.wangzhihao.blackmarket.service.WechatService;
import com.wangzhihao.blackmarket.service.WechatSessionService;
import com.wangzhihao.blackmarket.service.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/12/7.
 *
 * @author Wang Zhihao.
 */
@Component
public class WechatUtils {

    private static final String X_USER_SESSION_KEY = "X-User-Session-Key";

    @Autowired
    WechatService wechatService;

    @Autowired
    WechatUserService wechatUserService;

    @Autowired
    WechatSessionService wechatSessionService;

    @Autowired
    private HttpServletRequest request;

    private String getSessionKey() {
        String thirdSessionKey = request.getHeader(X_USER_SESSION_KEY);
        if (thirdSessionKey != null) {
            return thirdSessionKey;
        }
        throw new MissingSessionKeyException();
    }

    public WechatSession requireWechatSession() {
        String thirdSessionKey = getSessionKey();
        WechatSession wechatSession = wechatSessionService.getByThirdSessionKey(thirdSessionKey);
        if (wechatSession != null && !wechatSession.isExpired()) {
            return wechatSession;
        }
        throw new WechatSessionNotFoundException();
    }

    public WechatUser requireWechatUser() {
        WechatSession wechatSession = requireWechatSession();
        WechatUser wechatUser = wechatUserService.getByOpenId(wechatSession.getOpenId());
        if (wechatUser != null) {
            return wechatUser;
        }
        throw new WechatUserNotFoundException();
    }
}
