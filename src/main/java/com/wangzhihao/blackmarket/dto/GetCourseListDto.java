package com.wangzhihao.blackmarket.dto;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
public class GetCourseListDto {
    private Long year = 2018L;
    private String semester = "spring";

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "GetCourseListDto{" +
                "year=" + year +
                ", semester='" + semester + '\'' +
                '}';
    }
}
