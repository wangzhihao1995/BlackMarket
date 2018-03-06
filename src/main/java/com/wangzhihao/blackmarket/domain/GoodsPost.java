package com.wangzhihao.blackmarket.domain;

import com.wangzhihao.blackmarket.dto.AddGoodsPostDto;
import com.wangzhihao.blackmarket.enums.PostStautsEnum;

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
    private Integer status;
    private Integer mobileSwitch;
    private String wechat;
    private String content;
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

    public static GoodsPost getByAddGoodsPostDto(AddGoodsPostDto addGoodsPostDto) {
        GoodsPost goodsPost = new GoodsPost();
        goodsPost.setStudentId(addGoodsPostDto.getStudentId());
        goodsPost.setStatus(PostStautsEnum.NORMAL.getValue());
        goodsPost.setMobileSwitch(addGoodsPostDto.getMobileSwitch());
        goodsPost.setWechat(addGoodsPostDto.getWechat());
        goodsPost.setContent(addGoodsPostDto.getContent());
        goodsPost.setPv(0L);
        return goodsPost;
    }

    @Override
    public String toString() {
        return "GoodsPost{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", status=" + status +
                ", mobileSwitch=" + mobileSwitch +
                ", wechat='" + wechat + '\'' +
                ", content='" + content + '\'' +
                ", pv=" + pv +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
