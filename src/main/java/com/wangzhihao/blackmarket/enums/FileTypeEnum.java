package com.wangzhihao.blackmarket.enums;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/12/1.
 *
 * @author Wang Zhihao.
 */
public enum FileTypeEnum {
    /**
     * OTHER 其它
     * IMAGE 图片
     * DOCUMENT 文档
     * AUDIO 音频
     * VIDEO 视频
     */
    OTHER(0, "其它"),
    IMAGE(1, "图片"),
    DOCUMENT(2, "文档"),
    AUDIO(3, "音频"),
    VIDEO(4, "视频");
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
    FileTypeEnum(Integer value, String desc) {
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
