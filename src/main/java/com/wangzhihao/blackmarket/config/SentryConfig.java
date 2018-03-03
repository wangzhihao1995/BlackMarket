package com.wangzhihao.blackmarket.config;

import io.sentry.Sentry;
import io.sentry.SentryClient;
import io.sentry.SentryClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/10/17.
 *
 * @author Wang Zhihao.
 */
@Configuration
public class SentryConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${blackmarket.sentry.dsn}")
    private String dsn;

    /**
     * Sentry Client Instance.
     *
     * @return the sentry client.
     */
    @Bean
    public SentryClient sentryClient() {
        logger.info("init sentry client with dsn: {}", dsn);
        if ("".equals(dsn)) {
            return null;
        }
        Sentry.init(dsn);
        return SentryClientFactory.sentryClient();
    }
}
