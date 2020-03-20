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
public class ImageCodeProperties extends SmsCodeProperties {

    private int width = 100;  // 图型验证的长度
    private int height = 23; // 图型验证的高度

}
