package com.wangzhihao.blackmarket.service;

import com.wangzhihao.blackmarket.domain.CoursePost;
import org.springframework.stereotype.Service;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/30.
 *
 * @author Wang Zhihao.
 */
@Service
@FunctionalInterface
public interface CoursePostService {
    CoursePost getById(Long id);
}
