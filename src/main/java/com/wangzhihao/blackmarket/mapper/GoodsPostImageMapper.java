package com.wangzhihao.blackmarket.mapper;

import com.wangzhihao.blackmarket.domain.GoodsPostImage;
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
public interface GoodsPostImageMapper {

    void insert(GoodsPostImage goodsPostImage);

    GoodsPostImage findById(@Param("id") Long id);

    List<GoodsPostImage> findByPostId(@Param("postId") Long postId);
}
