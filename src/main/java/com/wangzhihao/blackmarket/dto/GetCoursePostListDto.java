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
    private String supply;
    private String demand;
    private String order = "desc";
    private Long closed = 0L;
    private Long start = 0L;
    private Long limit = 10L;

    public String getSupply() {
        return supply;
    }

    public void setSupply(String supply) {
        this.supply = supply;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Long getClosed() {
        return closed;
    }

    public void setClosed(Long closed) {
        this.closed = closed;
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
                "supply='" + supply + '\'' +
                ", demand='" + demand + '\'' +
                ", order='" + order + '\'' +
                ", closed=" + closed +
                ", start=" + start +
                ", limit=" + limit +
                '}';
    }
}
