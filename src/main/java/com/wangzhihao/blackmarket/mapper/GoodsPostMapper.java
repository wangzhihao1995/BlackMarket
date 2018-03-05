package com.wangzhihao.blackmarket.mapper;

import com.wangzhihao.blackmarket.domain.GoodsPost;
import com.wangzhihao.blackmarket.dto.GetGoodsPostListDto;
import com.wangzhihao.blackmarket.dto.UpdateGoodsPostDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    void update(UpdateGoodsPostDto updateGoodsPostDto);

    void updatePv(@Param("id") Long id, @Param("pv") Long pv);


}
