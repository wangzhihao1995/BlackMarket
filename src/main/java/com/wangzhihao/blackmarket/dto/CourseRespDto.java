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

    private Long id;
    private String name;
    private String teacher;
    private Integer credit;
    private Long year;
    private Integer semester;
    private List<CourseSchedule> schedules;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacher='" + teacher + '\'' +
                ", credit=" + credit +
                ", year=" + year +
                ", semester=" + semester +
                ", schedules=" + schedules +
                '}';
    }
}
