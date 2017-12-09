package com.wangzhihao.blackmarket.mapper;

import com.wangzhihao.blackmarket.domain.GoodsPost;
import com.wangzhihao.blackmarket.dto.GetGoodsPostListDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/30.
 *
 * @author Wang Zhihao.
 */
@Mapper
public interface GoodsPostMapper {
    void insert(GoodsPost goodsPost);

    GoodsPost findGoodsPostById(Long id);

    List<GoodsPost> findGoodsPosts(GetGoodsPostListDto getGoodsPostListDto);
}
