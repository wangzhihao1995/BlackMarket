package com.wangzhihao.blackmarket.unittest;

import com.wangzhihao.blackmarket.data.MockDoamin;
import com.wangzhihao.blackmarket.domain.File;
import com.wangzhihao.blackmarket.dto.UpdateFileDto;
import com.wangzhihao.blackmarket.enums.FileStautsEnum;
import com.wangzhihao.blackmarket.service.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileUnitTest {

    @Autowired
    FileService fileService;

    @Test
    @Sql("/dev.sql")
    public void testImageService() {
        File mockFile = MockDoamin.mockImage();
        fileService.add(mockFile);
        assertNotNull(mockFile.getId());
        assertEquals(fileService.getById(mockFile.getId()).getKey(), mockFile.getKey());
        UpdateFileDto updateFileDto = new UpdateFileDto();
        updateFileDto.setId(mockFile.getId());
        updateFileDto.setStatus(FileStautsEnum.NORMAL.getValue());
        fileService.update(updateFileDto);
        assertEquals(fileService.getById(updateFileDto.getId()).getStatus(), FileStautsEnum.NORMAL.getValue());
    }
}
