package com.wangzhihao.blackmarket.enums;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/12/1.
 *
 * @author Wang Zhihao.
 */
public enum GenderEnum {
    /**
     * UNKNOWN 未知
     * MALE 男
     * FEMALE 女
     */
    UNKNOWN(0L, "未知"),
    MALE(1L, "男"),
    FEMALE(2L, "女");
    /**
     * value
     */
    private Long value;

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
    GenderEnum(final Long value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * value getter
     *
     * @return value
     */
    public Long getValue() {
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
