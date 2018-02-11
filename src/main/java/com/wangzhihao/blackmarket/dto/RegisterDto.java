package com.wangzhihao.blackmarket.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
public class RegisterDto {
    @NotBlank
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "RegisterDto{" +
                "mobile='" + mobile + '\'' +
                '}';
    }
}
