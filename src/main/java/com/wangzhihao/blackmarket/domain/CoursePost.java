package com.wangzhihao.blackmarket.domain;

import com.wangzhihao.blackmarket.dto.AddCoursePostDto;
import com.wangzhihao.blackmarket.enums.CoursePostStautsEnumBlackMarket;

import java.util.Date;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
public class CoursePost {
    private Long id;
    private Long studentId;
    private Long demand;
    private Long supply;
    private Integer status;
    private Integer mobileSwitch;
    private String mobile;
    private String wechat;
    private String message;
    private Long pv;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getDemand() {
        return demand;
    }

    public void setDemand(Long demand) {
        this.demand = demand;
    }

    public Long getSupply() {
        return supply;
    }

    public void setSupply(Long supply) {
        this.supply = supply;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMobileSwitch() {
        return mobileSwitch;
    }

    public void setMobileSwitch(Integer mobileSwitch) {
        this.mobileSwitch = mobileSwitch;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getPv() {
        return pv;
    }

    public void setPv(Long pv) {
        this.pv = pv;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public static CoursePost getByAddCoursePostDto(AddCoursePostDto addCoursePostDto) {
        CoursePost coursePost = new CoursePost();
        coursePost.setStudentId(addCoursePostDto.getStudentId());
        coursePost.setSupply(addCoursePostDto.getSupply());
        coursePost.setDemand(addCoursePostDto.getDemand());
        coursePost.setStatus(CoursePostStautsEnumBlackMarket.NORMAL.getValue());
        coursePost.setMobileSwitch(addCoursePostDto.getMobileSwitch());
        coursePost.setWechat(addCoursePostDto.getWechat());
        coursePost.setMessage(addCoursePostDto.getMessage());
        coursePost.setPv(0L);
        return coursePost;
    }

    @Override
    public String toString() {
        return "CoursePost{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", demand=" + demand +
                ", supply=" + supply +
                ", status=" + status +
                ", mobileSwitch=" + mobileSwitch +
                ", mobile='" + mobile + '\'' +
                ", wechat='" + wechat + '\'' +
                ", message='" + message + '\'' +
                ", pv=" + pv +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
