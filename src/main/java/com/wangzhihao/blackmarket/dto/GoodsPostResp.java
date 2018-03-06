package com.wangzhihao.blackmarket.dto;

import java.util.Date;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
public class GoodsPostResp {

    private Long id;
    private StudentResp student;
    private Integer status;
    private Integer mobileSwitch;
    private String wechat;
    private String content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "GoodsPostResp{" +
                "id=" + id +
                ", student=" + student +
                ", status=" + status +
                ", mobileSwitch=" + mobileSwitch +
                ", wechat='" + wechat + '\'' +
                ", content='" + content + '\'' +
                ", pv=" + pv +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", hasViewedContact=" + hasViewedContact +
                '}';
    }
}
