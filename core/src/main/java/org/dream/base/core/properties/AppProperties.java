package org.dream.base.core.properties;


import lombok.Getter;
import lombok.Setter;

/**
 * 项目名称：security
 * 类 名 称：AppProperties
 * 类 描 述：
 * 创建时间：2020/3/28 16:03
 * 创 建 人：Lance.WU
 */
@Getter
@Setter
public class AppProperties {

    private String signUpUrl = SecurityConstants.DEFAULT_LOGIN_UP_URL;

    RedisProperties redis = new RedisProperties();

}
