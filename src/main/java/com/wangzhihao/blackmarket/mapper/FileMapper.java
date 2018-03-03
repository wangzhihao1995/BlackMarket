package com.wangzhihao.blackmarket.mapper;

import com.wangzhihao.blackmarket.domain.File;
import com.wangzhihao.blackmarket.dto.UpdateFileDto;
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
public interface FileMapper {
    void insert(File file);

    File findById(Long id);

    List<File> findByIds(List<Long> ids);

    void update(UpdateFileDto updateFileDto);
}
