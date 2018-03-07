package com.wangzhihao.blackmarket.service.impl;

import com.wangzhihao.blackmarket.domain.ConfigDomain;
import com.wangzhihao.blackmarket.mapper.ConfigMapper;
import com.wangzhihao.blackmarket.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/30.
 *
 * @author Wang Zhihao.
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    ConfigMapper configMapper;

    @Override
    public void add(ConfigDomain configDomain) {
        configMapper.insert(configDomain);
    }

    @Override
    public ConfigDomain getById(Long id) {
        return configMapper.findById(id);
    }

    @Override
    public ConfigDomain getByKey(String k) {
        return configMapper.findByKey(k);
    }

    @Override
    public void update(String k, String v) {
        configMapper.update(k, v);
    }
}
