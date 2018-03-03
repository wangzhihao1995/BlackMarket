package com.wangzhihao.blackmarket.enums;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/12/1.
 *
 * @author Wang Zhihao.
 */
public enum GenderEnum implements BlackMarketBaseEnum {
    /**
     * UNKNOWN 未知
     * MALE 男
     * FEMALE 女
     */
    UNKNOWN(0, "未知"),
    MALE(1, "男"),
    FEMALE(2, "女");
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
    GenderEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * value getter
     *
     * @return value
     */
    @Override
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
