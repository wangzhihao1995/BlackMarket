package com.wangzhihao.blackmarket.dto;

import com.wangzhihao.blackmarket.enums.BlackMarketEnum;
import com.wangzhihao.blackmarket.enums.MobileSwitchEnum;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
public class AddGoodsPostDto {
    private Long studentId;
    @BlackMarketEnum(enumClass = MobileSwitchEnum.class, message = "Invalid mobileSwitch!")
    private Integer mobileSwitch;
    private String wechat;
    @NotNull
    @NotBlank
    private String content;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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

    @Override
    public String toString() {
        return "AddGoodsPostDto{" +
                "studentId=" + studentId +
                ", mobileSwitch=" + mobileSwitch +
                ", wechat='" + wechat + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
