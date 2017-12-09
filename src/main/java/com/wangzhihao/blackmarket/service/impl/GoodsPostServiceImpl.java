package com.wangzhihao.blackmarket.service.impl;

import com.wangzhihao.blackmarket.domain.GoodsPost;
import com.wangzhihao.blackmarket.dto.GetGoodsPostListDto;
import com.wangzhihao.blackmarket.mapper.GoodsPostMapper;
import com.wangzhihao.blackmarket.service.GoodsPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void add(GoodsPost goodsPost) {
        goodsPostMapper.insert(goodsPost);
    }

    @Override
    public GoodsPost getById(Long id) {
        return goodsPostMapper.findGoodsPostById(id);
    }

    @Override
    public List<GoodsPost> getGoodsPostList(GetGoodsPostListDto getGoodsPostListDto) {
        return goodsPostMapper.findGoodsPosts(getGoodsPostListDto);
    }
}
