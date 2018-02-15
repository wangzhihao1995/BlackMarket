package com.wangzhihao.blackmarket.dto;

import com.wangzhihao.blackmarket.domain.Course;
import com.wangzhihao.blackmarket.domain.CourseSchedule;

import java.util.List;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
public class CourseRespDto {

    private Course course;
    private List<CourseSchedule> schedules;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<CourseSchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<CourseSchedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public String toString() {
        return "CourseRespDto{" +
                "course=" + course +
                ", schedules=" + schedules +
                '}';
    }
}
