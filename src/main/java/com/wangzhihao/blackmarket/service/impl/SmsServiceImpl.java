package com.wangzhihao.blackmarket.service.impl;

import com.wangzhihao.blackmarket.enums.SmsVerificationTypeEnum;
import com.wangzhihao.blackmarket.exception.BlackMarketException;
import com.wangzhihao.blackmarket.service.SmsService;
import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Description
 * <p>
 * </p>
 * DATE 11/02/2018.
 *
 * @author Wang Zhihao.
 */


/**
 * created by yaoshenghua on Aug 25, 2017 1:53:43 PM
 */
@Service
public class SmsServiceImpl implements SmsService {

    private static final String REDIS_KEY = "sms:verify:%d:%s";
    private static final String TOTAL_RETRY_KEY = "sms:verify:%d:%s:retry.total";
    private static final String TOTAL_SEND_KEY = "sms:verify:%d:%s:send.total";
    private static final Integer MAX_SEND_TOTAL = 5;
    private static final Integer MAX_RETRY_TOTAL = 5;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${blackmarket.yunpian.apikey}")
    private String apikey;

    private static String generateVerificationCode() {
        return RandomStringUtils.random(6, "0123456789");
    }

    @Override
    public Result<SmsSingleSend> singleSend(String mobile, String message) {
        YunpianClient yunpianClient = new YunpianClient(apikey).init();
        Map<String, String> param = yunpianClient.newParam(2);
        param.put(YunpianClient.MOBILE, mobile);
        param.put(YunpianClient.TEXT, message);
        Result<SmsSingleSend> result = yunpianClient.sms().single_send(param);
        yunpianClient.close();
        return result;
    }

    @Override
    public String add(String mobile, SmsVerificationTypeEnum smsVerificationTypeEnum) {
        String totalSendKey = String.format(TOTAL_SEND_KEY, smsVerificationTypeEnum.getValue(), mobile);
        Integer totalSend = 0;
        String totalSendVal = stringRedisTemplate.opsForValue().get(totalSendKey);
        if (totalSendVal != null) {
            totalSend = Integer.parseInt(totalSendVal);
        }
        if (totalSend >= MAX_SEND_TOTAL) {
            throw new BlackMarketException("已超出当日最大获取验证码次数，请24小时后尝试");
        }

        String key = String.format(REDIS_KEY, smsVerificationTypeEnum.getValue(), mobile);
        if (stringRedisTemplate.opsForValue().get(key) != null) {
            stringRedisTemplate.delete(key);
        }

        String verificationCode = generateVerificationCode();
        stringRedisTemplate.opsForValue().set(key, verificationCode);
        stringRedisTemplate.expire(key, 10, TimeUnit.MINUTES);
        stringRedisTemplate.opsForValue().increment(totalSendKey, 1);

        if (totalSendVal != null) {
            stringRedisTemplate.expire(totalSendKey, 1, TimeUnit.DAYS);
        }
        return verificationCode;
    }

    @Override
    public Boolean verify(String mobile, String verificationCode, SmsVerificationTypeEnum smsVerificationTypeEnum) {
        String key = String.format(REDIS_KEY, smsVerificationTypeEnum.getValue(), mobile);
        String totalRetryKey = String.format(TOTAL_RETRY_KEY, smsVerificationTypeEnum.getValue(), mobile);
        Integer totalRetry = 0;
        String totalRetryVal = stringRedisTemplate.opsForValue().get(totalRetryKey);
        if (totalRetryVal != null) {
            totalRetry = Integer.parseInt(totalRetryVal);
        }
        if (totalRetry >= MAX_RETRY_TOTAL) {
            throw new BlackMarketException("验证码错误输入次数过多，请稍后重新获取");
        }

        String cachedCode = stringRedisTemplate.opsForValue().get(key);
        if (verificationCode.equals(cachedCode)) {
            stringRedisTemplate.delete(key);
            stringRedisTemplate.delete(totalRetryKey);
            return true;
        }
        stringRedisTemplate.opsForValue().increment(totalRetryKey, 1);
        return false;
    }


}
