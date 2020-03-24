package org.dream.base.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 项目名称：security
 * 类 名 称：SessionProperties
 * 类 描 述：Session配置项
 * 创建时间：2020/3/24 22:04
 * 创 建 人：Lance.WU
 */
@Getter
@Setter
public class SessionProperties {

    /**
     * Session 失败效页面
     */
    private String sessionInvalidUrl = SecurityConstants.DEFAULT_SESSION_INVALID_URL;

    /**
     * 最大登录数
     */
    private int maximumSessions = 1;

    /**
     * 是否允许达到最大登录数，不再登录其它用户
     */
    private boolean maxSessionsPreventsLogin;


}

