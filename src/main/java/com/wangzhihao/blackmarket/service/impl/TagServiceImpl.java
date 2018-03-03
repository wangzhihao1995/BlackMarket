package com.wangzhihao.blackmarket.service.impl;

import com.wangzhihao.blackmarket.domain.Tag;
import com.wangzhihao.blackmarket.mapper.TagMapper;
import com.wangzhihao.blackmarket.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description
 * <p>
 * </p>
 * DATE 15/02/2018.
 *
 * @author Wang Zhihao.
 */
public class TagServiceImpl implements TagService {

    @Autowired
    TagMapper tagMapper;

    @Override
    public void add(Tag tag) {
        tagMapper.insert(tag);
    }

    @Override
    public Tag getById(Long id) {
        return tagMapper.findById(id);
    }

    @Override
    public Tag getByName(String name) {
        return tagMapper.findByName(name);
    }
}
