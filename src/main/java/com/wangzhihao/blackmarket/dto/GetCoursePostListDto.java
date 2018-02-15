package com.wangzhihao.blackmarket.dto;

import com.wangzhihao.blackmarket.enums.CoursePostStautsEnumBlackMarket;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
public class GetCoursePostListDto {
    private Long supply;
    private Long demand;
    private Long studentId;
    private String order = "desc";
    private Integer status = CoursePostStautsEnumBlackMarket.NORMAL.getValue();
    private Long start = 0L;
    private Long limit = 10L;

    public Long getSupply() {
        return supply;
    }

    public void setSupply(Long supply) {
        this.supply = supply;
    }

    public Long getDemand() {
        return demand;
    }

    public void setDemand(Long demand) {
        this.demand = demand;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "GetCoursePostListDto{" +
                "supply=" + supply +
                ", demand=" + demand +
                ", studentId=" + studentId +
                ", order='" + order + '\'' +
                ", status=" + status +
                ", start=" + start +
                ", limit=" + limit +
                '}';
    }
}
