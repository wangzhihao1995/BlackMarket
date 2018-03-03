package com.wangzhihao.blackmarket.service;

import com.wangzhihao.blackmarket.domain.Tag;
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
public interface TagService {
    void add(Tag tag);

    Tag getById(Long id);

    Tag getByName(String name);
}
