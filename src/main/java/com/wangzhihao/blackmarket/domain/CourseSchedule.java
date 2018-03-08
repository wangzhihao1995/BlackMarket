package com.wangzhihao.blackmarket.domain;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
public class CourseSchedule {
    private Long id;
    private Long courseId;
    private Integer day;
    private Integer start;
    private Integer end;
    private String frequency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "CourseSchedule{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", day=" + day +
                ", start=" + start +
                ", end=" + end +
                ", frequency='" + frequency + '\'' +
                '}';
    }
}
