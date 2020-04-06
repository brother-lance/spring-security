package org.dream.base.core.authorize;

import org.dream.base.core.properties.SecurityConstants;
import org.dream.base.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * 项目名称：security
 * 类 名 称：AuthorizeConfigProvider
 * 类 描 述：
 * 创建时间：2020/4/6 19:36
 * 创 建 人：Lance.WU
 */
@Component
public class DefaultAuthorizeConfigProvider implements AuthorizeConfigProvider  {


    @Autowired
    private SecurityProperties securityProperties;

    public  void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config){
        config.antMatchers(
                SecurityConstants.DEFAULT_UN_AUTHENTICATION_URL,
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_OPENID,
                securityProperties.getBrowser().getLoginPage(),
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                securityProperties.getBrowser().getSignUpUrl(),
                securityProperties.getBrowser().getSession().getSessionInvalidUrl() + ".json",
                securityProperties.getBrowser().getSession().getSessionInvalidUrl() + ".html",
                "/user/regist", "/social/signUp")
        .permitAll();

    }
}
