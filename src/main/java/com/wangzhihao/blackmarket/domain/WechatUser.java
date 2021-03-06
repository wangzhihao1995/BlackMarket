package com.wangzhihao.blackmarket.domain;

import com.wangzhihao.blackmarket.dto.UpdateWechatUserDto;

import java.util.Date;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
public class WechatUser {
    private Long id;
    private String openId;
    private String nickName;
    private String avatarUrl;
    private String city;
    private String country;
    private Integer gender;
    private String language;
    private String province;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public static WechatUser getByUpdateWechatUserDto(UpdateWechatUserDto updateWechatUserDto) {
        WechatUser wechatUser = new WechatUser();
        wechatUser.setOpenId(updateWechatUserDto.getOpenId());
        wechatUser.setNickName(updateWechatUserDto.getNickName());
        wechatUser.setAvatarUrl(updateWechatUserDto.getAvatarUrl());
        wechatUser.setCity(updateWechatUserDto.getCity());
        wechatUser.setCountry(updateWechatUserDto.getCountry());
        wechatUser.setGender(updateWechatUserDto.getGender());
        wechatUser.setLanguage(updateWechatUserDto.getLanguage());
        wechatUser.setProvince(updateWechatUserDto.getProvince());
        return wechatUser;
    }

    @Override
    public String toString() {
        return "WechatUser{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", gender=" + gender +
                ", language='" + language + '\'' +
                ", province='" + province + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
