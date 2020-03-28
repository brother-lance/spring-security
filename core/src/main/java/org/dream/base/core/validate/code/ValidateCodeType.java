package org.dream.base.core.validate.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 项目名称：security
 * 类 名 称：ValidateCodeType
 * 类 描 述：验证码类型
 * 创建时间：2020/3/21 00:47
 * 创 建 人：Lance.WU
 */
@Getter
@AllArgsConstructor
@ToString
public enum ValidateCodeType {

    sms, // 短信
    image, // 图片
    ;
}
