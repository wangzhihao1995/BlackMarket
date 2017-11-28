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
    private Long day;
    private Long start;
    private Long end;
    private Long frequency;

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

    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public Long getFrequency() {
        return frequency;
    }

    public void setFrequency(Long frequency) {
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
                ", frequency=" + frequency +
                '}';
    }
}
