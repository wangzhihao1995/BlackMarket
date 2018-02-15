package com.wangzhihao.blackmarket.service.impl;

import com.wangzhihao.blackmarket.domain.GoodsPostImage;
import com.wangzhihao.blackmarket.mapper.GoodsPostImageMapper;
import com.wangzhihao.blackmarket.service.GoodsPostImageService;
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
public class GoodsPostImageServiceImpl implements GoodsPostImageService {

    @Autowired
    GoodsPostImageMapper goodsPostImageMapper;

    @Override
    public void add(GoodsPostImage goodsPostImage) {
        goodsPostImageMapper.insert(goodsPostImage);
    }

    @Override
    public GoodsPostImage getById(Long id) {
        return goodsPostImageMapper.findById(id);
    }

    @Override
    public List<GoodsPostImage> getByPostId(Long postId) {
        return goodsPostImageMapper.findByPostId(postId);
    }
}
