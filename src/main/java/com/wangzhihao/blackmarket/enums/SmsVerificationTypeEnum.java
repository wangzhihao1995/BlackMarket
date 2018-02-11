package com.wangzhihao.blackmarket.enums;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/12/1.
 *
 * @author Wang Zhihao.
 */
public enum SmsVerificationTypeEnum {
    /**
     * REGISTER 注册
     * RESET_PASSWORD 重置密码
     */
    REGISTER(1, "注册"),
    RESET_PASSWORD(2, "重置密码");
    /**
     * value
     */
    private Integer value;

    /**
     * description
     */
    private String desc;

    /**
     * default constructor
     *
     * @param value value
     * @param desc  desc
     */
    SmsVerificationTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * value getter
     *
     * @return value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * desc getter
     *
     * @return desc
     */
    public String getDesc() {
        return desc;
    }
}
