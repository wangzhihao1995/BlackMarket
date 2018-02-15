package com.wangzhihao.blackmarket.service;

import com.wangzhihao.blackmarket.domain.GoodsPostImage;
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
public interface GoodsPostImageService {
    void add(GoodsPostImage goodsPostImage);

    GoodsPostImage getById(Long id);

    List<GoodsPostImage> getByPostId(Long postId);
}
