package com.wangzhihao.blackmarket.service.impl;

import com.wangzhihao.blackmarket.domain.GoodsPost;
import com.wangzhihao.blackmarket.mapper.GoodsPostMapper;
import com.wangzhihao.blackmarket.service.GoodsPostService;
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
public class GoodsPostServiceImpl implements GoodsPostService {

    @Autowired
    GoodsPostMapper goodsPostMapper;

    @Override
    public GoodsPost getById(Long id) {
        return goodsPostMapper.findGoodsPostById(id);
    }
}
