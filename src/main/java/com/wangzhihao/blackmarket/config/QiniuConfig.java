package com.wangzhihao.blackmarket.config;

import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/10/19.
 *
 * @author Shanbin.
 */

@Configuration
public class QiniuConfig {

    @Value("${blackmarket.qiniu.accessKey}")
    private String qiniuAccessKey;

    @Value("${blackmarket.qiniu.secretKey}")
    private String qiniuSecretKey;

    @Bean
    public Auth getClient() {
        return Auth.create(qiniuAccessKey, qiniuSecretKey);
    }
}
