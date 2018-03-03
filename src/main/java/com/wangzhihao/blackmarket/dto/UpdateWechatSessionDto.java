package com.wangzhihao.blackmarket.dto;

import java.time.LocalDateTime;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
public class UpdateWechatSessionDto {
    private Long id;
    private String sessionKey;
    private String thirdSessionKey;
    private static LocalDateTime expireTime = LocalDateTime.now().plusDays(1);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getThirdSessionKey() {
        return thirdSessionKey;
    }

    public void setThirdSessionKey(String thirdSessionKey) {
        this.thirdSessionKey = thirdSessionKey;
    }

    public static LocalDateTime getExpireTime() {
        return expireTime;
    }

    public static void setExpireTime(LocalDateTime expireTime) {
        UpdateWechatSessionDto.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "UpdateWechatSessionDto{" +
                "id=" + id +
                ", sessionKey='" + sessionKey + '\'' +
                ", thirdSessionKey='" + thirdSessionKey + '\'' +
                '}';
    }
}
