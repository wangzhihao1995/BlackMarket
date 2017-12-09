package com.wangzhihao.blackmarket.service.impl;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.wangzhihao.blackmarket.service.QiniuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Description
 * <p>
 * </p>
 * DATE 09/12/2017.
 *
 * @author Wang Zhihao.
 */
@Service
public class QiniuServiceImpl implements QiniuService {

    private static final String BUCKET = "blackmarket";
    private static final Integer EXPIRE_SECONDS = 3600;
    private static final String CALLBACK_URL = "http://blackmarket.cn/api/v1/qiniu/callback";

    @Autowired
    Auth qiniuAuth;

    @Override
    public String fetchToken(Long studentId) {
        String key = String.format("img-stu%s-%s", studentId, UUID.randomUUID());
        StringMap putPolicy = new StringMap();
        putPolicy.put("callbackUrl", CALLBACK_URL);
        putPolicy.put("callbackBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)}");
        putPolicy.put("callbackBodyType", "application/json");
        return qiniuAuth.uploadToken(BUCKET, key, EXPIRE_SECONDS, putPolicy);
    }
}
