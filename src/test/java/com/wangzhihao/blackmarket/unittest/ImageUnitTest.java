package com.wangzhihao.blackmarket.unittest;

import com.wangzhihao.blackmarket.data.MockDoamin;
import com.wangzhihao.blackmarket.domain.Image;
import com.wangzhihao.blackmarket.dto.UpdateImageDto;
import com.wangzhihao.blackmarket.service.ImageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

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
public class ImageUnitTest {

    @Autowired
    ImageService imageService;

    @Test
    @Sql("/dev.sql")
    public void testImageService() {
        Image mockImage = MockDoamin.mockImage();
        imageService.add(mockImage);
        assertNotNull(mockImage.getId());
        assertEquals(imageService.getById(mockImage.getId()).getKey(), mockImage.getKey());
        UpdateImageDto updateImageDto = new UpdateImageDto();
        updateImageDto.setId(mockImage.getId());
        updateImageDto.setStatus(2L);
        imageService.update(updateImageDto);
        assertTrue(imageService.getById(updateImageDto.getId()).getStatus() == 2);
    }
}
