package com.wangzhihao.blackmarket.domain;

import java.util.Date;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
public class Student {
    private Long id;
    private Long wechatUserId;
    private String name;
    private String mobile;
    private String openId;
    private Integer type;
    private Integer grade;
    private Integer status;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWechatUserId() {
        return wechatUserId;
    }

    public void setWechatUserId(Long wechatUserId) {
        this.wechatUserId = wechatUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", wechatUserId=" + wechatUserId +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", openId='" + openId + '\'' +
                ", type=" + type +
                ", grade=" + grade +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
