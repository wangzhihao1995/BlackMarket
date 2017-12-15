package com.wangzhihao.blackmarket.mapper;

import com.wangzhihao.blackmarket.domain.Image;
import com.wangzhihao.blackmarket.dto.UpdateImageDto;
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
public interface ImageMapper {
    void insert(Image image);

    Image findImageById(Long id);

    List<Image> findImagesByIds(List<Long> ids);

    void update(UpdateImageDto updateImageDto);
}
