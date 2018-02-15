package com.wangzhihao.blackmarket.enums;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/12/1.
 *
 * @author Wang Zhihao.
 */
public enum CoursePostStautsEnumBlackMarket implements BlackMarketBaseEnum {
    /**
     * NORMAL
     * SUCCEED
     * CLOSED
     */
    NORMAL(1, "正常"),
    SUCCEED(2, "成功"),
    CLOSED(3, "关闭");
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
    CoursePostStautsEnumBlackMarket(Integer value, String desc) {
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
