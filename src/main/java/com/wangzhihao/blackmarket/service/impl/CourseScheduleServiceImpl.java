package com.wangzhihao.blackmarket.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wangzhihao.blackmarket.domain.CourseSchedule;
import com.wangzhihao.blackmarket.mapper.CourseScheduleMapper;
import com.wangzhihao.blackmarket.service.CourseScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
public class CourseScheduleServiceImpl implements CourseScheduleService {

    @Autowired
    CourseScheduleMapper courseScheduleMapper;

    @Override
    public void add(CourseSchedule courseSchedule) {
        courseScheduleMapper.insert(courseSchedule);
    }

    @Override
    public CourseSchedule getById(Long id) {
        return courseScheduleMapper.findById(id);
    }

    @Override
    public List<CourseSchedule> getCourseSchedulesByCourseId(Long courseId) {
        return courseScheduleMapper.findByCourseId(courseId);
    }

    @Override
    public Map<Long, List<CourseSchedule>> getCourseSchedulesByCourseIds(Set<Long> courseIds) {
        if (courseIds.isEmpty()) {
            return Maps.newHashMap();
        }
        Map<Long, List<CourseSchedule>> map = new HashMap<>();
        List<CourseSchedule> courseSchedules = courseScheduleMapper.findByCourseIds(courseIds);
        for (CourseSchedule courseSchedule : courseSchedules) {
            List<CourseSchedule> list = map.get(courseSchedule.getCourseId());
            if (list != null) {
                list.add(courseSchedule);
            } else {
                map.put(courseSchedule.getCourseId(), Lists.newArrayList(courseSchedule));
            }
        }
        return map;
    }
}
