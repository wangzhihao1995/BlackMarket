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
public class AddCoursePostDto {
    private Long studentId;
    @NotNull
    @BlackMarketEnum(enumClass = MobileSwitchEnum.class, message = "Invalid mobileSwitch!")
    private Integer mobileSwitch;
    private String wechat;
    @NotNull
    @NotBlank
    private String message;
    @NotNull
    private Long supply;
    @NotNull
    private Long demand;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getSupply() {
        return supply;
    }

    public void setSupply(Long supply) {
        this.supply = supply;
    }

    public Long getDemand() {
        return demand;
    }

    public void setDemand(Long demand) {
        this.demand = demand;
    }

    @Override
    public String toString() {
        return "AddCoursePostDto{" +
                "studentId=" + studentId +
                ", mobileSwitch=" + mobileSwitch +
                ", wechat='" + wechat + '\'' +
                ", message='" + message + '\'' +
                ", supply=" + supply +
                ", demand=" + demand +
                '}';
    }
}
