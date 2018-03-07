package com.wangzhihao.blackmarket.service;

import com.wangzhihao.blackmarket.domain.ConfigDomain;
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
public interface ConfigService {

    void add(ConfigDomain configDomain);

    ConfigDomain getById(Long id);

    ConfigDomain getByKey(String key);

    void update(String key, String value);
}
