package com.wangzhihao.blackmarket.enums;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/12/1.
 *
 * @author Wang Zhihao.
 */
public enum MobileSwitchEnum implements BlackMarketBaseEnum {
    /**
     * OFF
     * ON
     */
    OFF(0, "OFF"),
    ON(1, "ON");
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
    MobileSwitchEnum(Integer value, String desc) {
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
