package org.dream.base.app.config;

import org.dream.base.core.authorize.AuthorizeConfigManager;
import org.dream.base.core.authentication.soial.OpenIdAuthenticationSecurityConfig;
import org.dream.base.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import org.dream.base.core.properties.SecurityConstants;
import org.dream.base.core.properties.SecurityProperties;
import org.dream.base.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.annotation.Resource;


/**
 * 项目名称：security
 * 类 名 称：DefaultResourceServerConfiguration
 * 类 描 述：
 * 创建时间：2020/3/25 14:36
 * 创 建 人：Lance.WU
 */
@Configuration
@EnableResourceServer
public class DefaultResourceServerConfiguration extends ResourceServerConfigurerAdapter {


    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Resource
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Resource
    SpringSocialConfigurer springSocialConfigurer;

    @Autowired
    AuthorizeConfigManager authorizeConfigManager;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);

        http
                .apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(springSocialConfigurer)
                .and()
                .apply(openIdAuthenticationSecurityConfig)
                .and()

                .csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());

    }


    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

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
