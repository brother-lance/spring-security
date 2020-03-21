package org.dream.base.core.config;

import org.dream.base.core.properties.SecurityConstants;
import org.dream.base.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 项目名称：security
 * 类 名 称：AbstractChannelSecurityConfig
 * 类 描 述：
 * 创建时间：2020/3/21 01:10
 * 创 建 人：Lance.WU
 */
public abstract class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    SecurityProperties securityProperties;

    /**
     * 登录验证配置
     *
     * @param http 前端需要验证的逻辑
     * @throws Exception
     */
    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(securityProperties.getBrowser().getLoginPage())
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM) //验证登录的自定义方法
                .successHandler(authenticationSuccessHandler) // 成功之后返回
                .failureHandler(authenticationFailureHandler);
    }
}
