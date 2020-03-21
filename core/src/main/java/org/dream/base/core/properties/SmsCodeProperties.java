package org.dream.base.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 项目名称：security
 * 类 名 称：ImageCodeProperties
 * 类 描 述：验证码验证配置
 * 创建时间：2020/3/19 19:18
 * 创 建 人：Lance.WU
 */
@Getter
@Setter
public class SmsCodeProperties {

    private int length = 6;// 验证码位长度
    private int expireIn = 60;// 有效时间
    private String url = ""; // 要拦截的URL
    private String mobileInputName = SecurityConstants.DEFAULT_MOBILE_INPUT_NAME_KEY; // 手机号输入值的字段名称
    private String codeInputName = SecurityConstants.DEFAULT_CODE_INPUT_NAME_SMS_KEY; // 验证码字段名称

}
