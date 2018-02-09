package com.wangzhihao.blackmarket.service.impl;

import com.wangzhihao.blackmarket.domain.File;
import com.wangzhihao.blackmarket.dto.UpdateFileDto;
import com.wangzhihao.blackmarket.mapper.FileMapper;
import com.wangzhihao.blackmarket.service.FileService;
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
public class FileServiceImpl implements FileService {

    @Autowired
    FileMapper fileMapper;

    @Override
    public void add(File file) {
        fileMapper.insert(file);
    }

    @Override
    public File getById(Long id) {
        return fileMapper.findById(id);
    }

    @Override
    public List<File> getFileList(List<Long> ids) {
        return fileMapper.findByIds(ids);
    }

    @Override
    public void update(UpdateFileDto updateFileDto) {
        fileMapper.update(updateFileDto);
    }
}
