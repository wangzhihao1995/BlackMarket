package com.wangzhihao.blackmarket.service;

import com.wangzhihao.blackmarket.domain.CourseSchedule;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/30.
 *
 * @author Wang Zhihao.
 */
@Service
public interface CourseScheduleService {
    void add(CourseSchedule courseSchedule);

    CourseSchedule getById(Long id);

    List<CourseSchedule> getCourseSchedulesByCourseId(Long courseId);

    Map<Long, List<CourseSchedule>> getCourseSchedulesByCourseIds(Set<Long> courseIds);

}
