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
public class GoodsPost {
    private Long id;
    private Long studentId;
    private Long status;
    private String mobile;
    private String wechat;
    private String message;
    private String imgs;
    private Long pv = 0L;
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

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
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

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
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

    @Override
    public String toString() {
        return "GoodsPost{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", status=" + status +
                ", mobile='" + mobile + '\'' +
                ", wechat='" + wechat + '\'' +
                ", message='" + message + '\'' +
                ", imgs='" + imgs + '\'' +
                ", pv=" + pv +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
