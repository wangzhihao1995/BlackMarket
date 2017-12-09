package com.wangzhihao.blackmarket.service;

import com.wangzhihao.blackmarket.domain.Image;
import com.wangzhihao.blackmarket.dto.UpdateImageDto;
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
public interface ImageService {

    void add(Image image);

    Image getById(Long id);

    List<Image> getImageList(List<Long> ids);

    void update(UpdateImageDto updateImageDto);
}
