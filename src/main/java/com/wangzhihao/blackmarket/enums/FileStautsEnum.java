package com.wangzhihao.blackmarket.enums;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/12/1.
 *
 * @author Wang Zhihao.
 */
public enum FileStautsEnum {
    /**
     * UNKNOWN 未知
     * MALE 男
     * FEMALE 女
     */
    NOT_READY(0, "未就绪"),
    NORMAL(1, "正常"),
    ABNORMAL(2, "不正常");
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
    FileStautsEnum(Integer value, String desc) {
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
