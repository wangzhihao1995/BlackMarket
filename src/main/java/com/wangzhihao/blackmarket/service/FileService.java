package com.wangzhihao.blackmarket.service;

import com.wangzhihao.blackmarket.domain.File;
import com.wangzhihao.blackmarket.dto.UpdateFileDto;
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
public interface FileService {

    void add(File file);

    File getById(Long id);

    List<File> getFileList(List<Long> ids);

    void update(UpdateFileDto updateFileDto);
}
