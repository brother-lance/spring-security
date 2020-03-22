package org.dream.base.core.validate.code.sms;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.dream.base.core.authentication.mobile.SmsCodeSender;

/**
 * 项目名称：security
 * 类 名 称：DefaultSmsCodeSender
 * 类 描 述：默认的短信发送者
 * 创建时间：2020/3/20 18:52
 * 创 建 人：Lance.WU
 */
@Setter
@Getter
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobileNo, String validateCode) {
        log.info("发送短信实现，手机号：{}， 验证码： {}", mobileNo, validateCode);

        // TODO 此处需要用户自己实例短信发送的内容。
    }
}
