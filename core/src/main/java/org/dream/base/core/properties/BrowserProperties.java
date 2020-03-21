package org.dream.base.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 项目名称：security
 * 类 名 称：BrowserProperties
 * 类 描 述：浏览器配置项类
 * 创建时间：2020/3/19 12:12
 * 创 建 人：Lance.WU
 */
@Getter
@Setter
public class BrowserProperties {

    private String loginPage = SecurityConstants.DEFAULT_LOGIN_LOGIN_URL;

    private LoginType loginType = LoginType.JSON;

    private int rememberMeSeconds = 60 * 60 * 24 * 7 * 2; //两周登陆保存时间
}
