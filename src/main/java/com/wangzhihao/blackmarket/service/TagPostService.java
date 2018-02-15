package com.wangzhihao.blackmarket.service;

import com.wangzhihao.blackmarket.domain.TagPost;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/29.
 *
 * @author Wang Zhihao.
 */
@Service
public interface TagPostService {
    void add(TagPost tagPost);

    List<TagPost> getByTagId(Long postId, Long start, Long limit);

    List<TagPost> getByPostId(Long postId);

}
