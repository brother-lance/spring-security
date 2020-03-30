package org.dream.base.app.rpc;

import org.dream.base.app.social.openid.AppSocialSignUpUtils;
import org.dream.base.core.support.DefaultSocialUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目名称：security
 * 类 名 称：AppSecurityAPI
 * 类 描 述：
 * 创建时间：2020/3/30 10:33
 * 创 建 人：Lance.WU
 */
@RestController
public class AppSecurityAPI  {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private AppSocialSignUpUtils appSocialSignUpUtils;

    @GetMapping("/social/signUp")
    public DefaultSocialUserInfo getDefaultSocialUserInfo(HttpServletRequest request){

        DefaultSocialUserInfo userInfo = new DefaultSocialUserInfo();
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        userInfo.setHeadimg(connection.getImageUrl());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());

        appSocialSignUpUtils.saveConnectionData(new ServletWebRequest(request),
                connection.createData());


        return userInfo;
    }

}
