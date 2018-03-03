package com.wangzhihao.blackmarket.dto;

import com.wangzhihao.blackmarket.domain.Course;
import com.wangzhihao.blackmarket.domain.CourseSchedule;
import com.wangzhihao.blackmarket.domain.Student;

import java.util.Date;
import java.util.List;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
public class CoursePostResp {

    private Long id;
    private StudentResp student;
    private Course demand;
    private Course supply;
    private Integer status;
    private Integer mobileSwitch;
    private String wechat;
    private String message;
    private Long pv;
    private Date createTime;
    private Date updateTime;
    private Boolean hasViewedContact = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudentResp getStudent() {
        return student;
    }

    public void setStudent(StudentResp student) {
        this.student = student;
    }

    public Course getDemand() {
        return demand;
    }

    public void setDemand(Course demand) {
        this.demand = demand;
    }

    public Course getSupply() {
        return supply;
    }

    public void setSupply(Course supply) {
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

    public Boolean getHasViewedContact() {
        return hasViewedContact;
    }

    public void setHasViewedContact(Boolean hasViewedContact) {
        this.hasViewedContact = hasViewedContact;
    }

    @Override
    public String toString() {
        return "CoursePostResp{" +
                "id=" + id +
                ", student=" + student +
                ", demand=" + demand +
                ", supply=" + supply +
                ", status=" + status +
                ", mobileSwitch=" + mobileSwitch +
                ", wechat='" + wechat + '\'' +
                ", message='" + message + '\'' +
                ", pv=" + pv +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", hasViewedContact=" + hasViewedContact +
                '}';
    }
}
