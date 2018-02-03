package com.wangzhihao.blackmarket.controller;

import com.alibaba.fastjson.JSONObject;
import com.wangzhihao.blackmarket.domain.WechatSession;
import com.wangzhihao.blackmarket.dto.UpdateWechatUserDto;
import com.wangzhihao.blackmarket.exception.MissingJscodeException;
import com.wangzhihao.blackmarket.service.WechatService;
import com.wangzhihao.blackmarket.service.WechatSessionService;
import com.wangzhihao.blackmarket.service.WechatUserService;
import com.wangzhihao.blackmarket.utils.WechatUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;


/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
@RestController
@RequestMapping(value = "/api/wechat")
public class WechatController {

    private static final String OPENID = "openid";
    private static final String SESSION_KEY = "session_key";

    @Autowired
    WechatService wechatService;

    @Autowired
    WechatUserService wechatUserService;

    @Autowired
    WechatSessionService wechatSessionService;

    @Autowired
    WechatUtils wechatUtils;

    @RequestMapping(value = "/jscode2session", method = RequestMethod.GET)
    ResponseEntity jscode2session(@Param("code") String code) {
        if (code != null) {
            JSONObject res = wechatService.jscode2session(code);
            String openId = (String) res.get(OPENID);
            String sessionKey = (String) res.get(SESSION_KEY);
            WechatSession wechatSession = wechatSessionService.add(openId, sessionKey);
            HashMap<String, String> map = new HashMap<>();
            map.put(SESSION_KEY, wechatSession.getThirdSessionKey());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        throw new MissingJscodeException();
    }

    @ApiOperation(value = "Check Session")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/check_session", method = RequestMethod.GET)
    ResponseEntity checkSession() {
        return new ResponseEntity<>(wechatUtils.requireWechatSession(), HttpStatus.OK);
    }

    @ApiOperation(value = "Update Wechat User")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-User-Session-Key", paramType = "header")})
    @RequestMapping(value = "/user", method = {RequestMethod.POST, RequestMethod.PUT})
    ResponseEntity updateWecahtUser(@Valid @RequestBody UpdateWechatUserDto updateWechatUserDto) {
        updateWechatUserDto.setOpenId(wechatUtils.requireWechatSession().getOpenId());
        wechatUserService.updateWechatUser(updateWechatUserDto);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
