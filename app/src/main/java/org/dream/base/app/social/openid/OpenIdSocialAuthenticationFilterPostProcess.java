package org.dream.base.app.social.openid;

import org.dream.base.app.authentication.AuthenticationSuccessHandlerImpl;
import org.dream.base.core.social.SocialAuthenticationFilterPostProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 项目名称：security
 * 类 名 称：OpenIdSocialAuthenticationFilterPostProcess
 * 类 描 述：处理扫描后是跳转页面清空是返回信息
 * 创建时间：2020/3/28 21:31
 * 创 建 人：Lance.WU
 */
@Component
public class OpenIdSocialAuthenticationFilterPostProcess implements SocialAuthenticationFilterPostProcess {

    @Autowired
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;

    @Override
    public void process(SocialAuthenticationFilter filter) {
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
    }
}
