package org.dream.base.core.authentication.mobile;


/**
 * 项目名称：security
 * 类 名 称：SmsCodeSender
 * 类 描 述：短信发送接口
 * 创建时间：2020/3/20 18:46
 * 创 建 人：Lance.WU
 */
public interface SmsCodeSender {

    /**
     * 发送短信的接口
     * @param mobileNo 手机号
     * @param validateCode 验证码
     */
    void send(String mobileNo, String validateCode);
}
