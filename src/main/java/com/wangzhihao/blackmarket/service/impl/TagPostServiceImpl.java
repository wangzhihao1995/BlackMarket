package com.wangzhihao.blackmarket.service.impl;

import com.wangzhihao.blackmarket.domain.TagPost;
import com.wangzhihao.blackmarket.mapper.TagPostMapper;
import com.wangzhihao.blackmarket.service.TagPostService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Description
 * <p>
 * </p>
 * DATE 15/02/2018.
 *
 * @author Wang Zhihao.
 */
public class TagPostServiceImpl implements TagPostService {

    @Autowired
    TagPostMapper tagPostMapper;

    @Override
    public void add(TagPost tagPost) {
        tagPostMapper.insert(tagPost);
    }

    @Override
    public List<TagPost> getByTagId(Long tagId, Long start, Long limit) {
        return tagPostMapper.findByTagId(tagId, start, limit);
    }

    @Override
    public List<TagPost> getByPostId(Long postId) {
        return tagPostMapper.findByPostId(postId);
    }
}
