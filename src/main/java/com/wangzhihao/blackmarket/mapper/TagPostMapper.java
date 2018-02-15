package com.wangzhihao.blackmarket.mapper;

import com.wangzhihao.blackmarket.domain.TagPost;
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
public interface TagPostMapper {

    void insert(TagPost tagPost);

    TagPost findById(@Param("id") Long id);

    List<TagPost> findByPostId(@Param("postId") Long postId);

    List<TagPost> findByTagId(@Param("tagId") Long tagId,
                              @Param("start") Long start,
                              @Param("limit") Long limit);

}
