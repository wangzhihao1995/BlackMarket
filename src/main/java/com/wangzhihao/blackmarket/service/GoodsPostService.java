package com.wangzhihao.blackmarket.service;

import com.wangzhihao.blackmarket.domain.GoodsPost;
import com.wangzhihao.blackmarket.dto.GetGoodsPostListDto;
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
public interface GoodsPostService {
    void add(GoodsPost goodsPost);

    GoodsPost getById(Long id);

    List<GoodsPost> getGoodsPostList(GetGoodsPostListDto getGoodsPostListDto);
}
