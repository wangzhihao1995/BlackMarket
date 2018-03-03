package com.wangzhihao.blackmarket.config;

import com.wangzhihao.blackmarket.filter.WechatSessionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/29.
 *
 * @author Wang Zhihao.
 */
@Configuration
@Component
public class FilterConfig {

    @Autowired
    private WechatSessionFilter wechatSessionFilter;

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(wechatSessionFilter);
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/api/course/*");
        urlPatterns.add("/api/student/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}
