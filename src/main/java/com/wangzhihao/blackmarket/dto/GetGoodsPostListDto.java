package com.wangzhihao.blackmarket.dto;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
public class GetGoodsPostListDto {
    private String order = "desc";
    private Long studentId;
    private Long status;
    private Long start = 0L;
    private Long limit = 10L;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
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
        return "GetGoodsPostListDto{" +
                "order='" + order + '\'' +
                ", studentId=" + studentId +
                ", status=" + status +
                ", start=" + start +
                ", limit=" + limit +
                '}';
    }
}
