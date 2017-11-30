package com.wangzhihao.blackmarket.service;

import com.wangzhihao.blackmarket.domain.Student;
import org.springframework.stereotype.Service;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/29.
 *
 * @author Wang Zhihao.
 */
@Service
@FunctionalInterface
public interface StudentService {
    Student getById(Long id);
}
