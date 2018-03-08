package com.wangzhihao.blackmarket.dto;

import com.wangzhihao.blackmarket.enums.SemesterEnum;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
public class GetCourseListDto {
    private Integer year = 2018;
    private Integer semester = SemesterEnum.SPRING.getValue();

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "GetCourseListDto{" +
                "year=" + year +
                ", semester=" + semester +
                '}';
    }
}
