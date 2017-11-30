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
    private String nickname;
    private String avatarUrl;
    private String city;
    private String country;
    private Long gender;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
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

    public void setByUpdateWechatUserDto(UpdateWechatUserDto updateWechatUserDto) {
        this.openId = updateWechatUserDto.getOpenId();
        this.nickname = updateWechatUserDto.getNickname();
        this.avatarUrl = updateWechatUserDto.getAvatarUrl();
        this.city = updateWechatUserDto.getCity();
        this.country = updateWechatUserDto.getCountry();
        this.gender = updateWechatUserDto.getGender();
        this.language = updateWechatUserDto.getLanguage();
        this.province = updateWechatUserDto.getProvince();
    }

    @Override
    public String toString() {
        return "WechatUser{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", nickname='" + nickname + '\'' +
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
