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
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();

}
