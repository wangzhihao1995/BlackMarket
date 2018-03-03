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
    private String studentId;
    @BlackMarketEnum(enumClass = MobileSwitchEnum.class, message = "Invalid mobileSwitch!")
    private Long mobileSwitch;
    private String wechat;
    @NotNull
    @NotBlank
    private String message;
    private String imgs;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Long getMobileSwitch() {
        return mobileSwitch;
    }

    public void setMobileSwitch(Long mobileSwitch) {
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

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    @Override
    public String toString() {
        return "AddGoodsPostDto{" +
                "studentId='" + studentId + '\'' +
                ", mobileSwitch=" + mobileSwitch +
                ", wechat='" + wechat + '\'' +
                ", message='" + message + '\'' +
                ", imgs='" + imgs + '\'' +
                '}';
    }
}
