package org.dream.base.app.config;

import org.dream.base.app.authentication.socil.openid.OpenIdAuthenticationSecurityConfig;
import org.dream.base.app.authentication.socil.openid.OpenIdAuthenticationToken;
import org.dream.base.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import org.dream.base.core.properties.SecurityConstants;
import org.dream.base.core.properties.SecurityProperties;
import org.dream.base.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
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
                .authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_UN_AUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_OPENID,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        securityProperties.getBrowser().getSignUpUrl(),
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl() + ".json",
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl() + ".html",
                        "/user/regist")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
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
