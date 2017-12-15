package com.wangzhihao.blackmarket.service.impl;

import com.wangzhihao.blackmarket.domain.Image;
import com.wangzhihao.blackmarket.dto.UpdateImageDto;
import com.wangzhihao.blackmarket.mapper.ImageMapper;
import com.wangzhihao.blackmarket.service.ImageService;
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
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageMapper imageMapper;

    @Override
    public void add(Image image) {
        imageMapper.insert(image);
    }

    @Override
    public Image getById(Long id) {
        return imageMapper.findImageById(id);
    }

    @Override
    public List<Image> getImageList(List<Long> ids) {
        return imageMapper.findImagesByIds(ids);
    }

    @Override
    public void update(UpdateImageDto updateImageDto) {
        imageMapper.update(updateImageDto);
    }
}
