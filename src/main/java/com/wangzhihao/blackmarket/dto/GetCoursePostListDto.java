package com.wangzhihao.blackmarket.dto;

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
    private String order = "desc";
    private Long status = 0L;
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

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
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
        return "GetCoursePostListDto{" +
                "supply=" + supply +
                ", demand=" + demand +
                ", order='" + order + '\'' +
                ", status=" + status +
                ", start=" + start +
                ", limit=" + limit +
                '}';
    }
}
