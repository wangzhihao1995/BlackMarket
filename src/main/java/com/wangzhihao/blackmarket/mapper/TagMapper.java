package com.wangzhihao.blackmarket.mapper;

import com.wangzhihao.blackmarket.domain.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/30.
 *
 * @author Wang Zhihao.
 */
@Mapper
public interface TagMapper {

    void insert(Tag tag);

    Tag findById(@Param("id") Long id);

    Tag findByName(@Param("name") String name);

}
