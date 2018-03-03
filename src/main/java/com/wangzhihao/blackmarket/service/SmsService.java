package com.wangzhihao.blackmarket.service;

import com.wangzhihao.blackmarket.enums.SmsVerificationTypeEnum;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/29.
 *
 * @author Wang Zhihao.
 */

public interface SmsService {

    Result<SmsSingleSend> singleSend(String mobile, String message);

    String add(String mobile, SmsVerificationTypeEnum smsVerificationTypeEnum);

    Boolean verify(String mobile, String verificationCode, SmsVerificationTypeEnum smsVerificationTypeEnum);

}
