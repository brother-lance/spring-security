package org.dream.base.brower;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.dream.base.brower.support.SimpleResponse;
import org.dream.base.brower.support.SocialUserInfo;
import org.dream.base.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Provider;

/**
 * 项目名称：security
 * 类 名 称：BrowserSecurtityAPI
 * 类 描 述：默认认证
 * 创建时间：2020/3/19 11:05
 * 创 建 人：Lance.WU
 */
@RestController
@Slf4j
public class BrowserSecurityAPI {

    /**
     * 请求缓存信息
     */
    private HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
    /**
     * 跳转策略
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    /**
     * 当需要身份证时，跳转到这里
     *
     * @param request  请求对像
     * @param response 响应对应
     * @return 对像
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public Object requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SavedRequest saveRequest = requestCache.getRequest(request, response);

        if (saveRequest != null) {
            /** 判断是否HTML请求 */
            String targetUrl = saveRequest.getRedirectUrl();

            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html"))
                log.info("跳转转发的请求地址：" + targetUrl);
            redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
        }

        return new SimpleResponse("访问的服务需要身份认证，请引导用户跳转登录页面");
    }

    /**
     * 获取社交登录的用户信息
     *
     * @param request 请求对象
     */
    @GetMapping("/authentication/require")
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
        SocialUserInfo userInfo = new SocialUserInfo();
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        userInfo.setHeadimg(connection.getImageUrl());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        return userInfo;
    }

    /**
     * 获取社交登录的用户信息
     *
     * @param request 请求对象
     */
    @GetMapping("/session/invalid")
    public SimpleResponse getSocialUserInfo(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json; charset=UTF-8");
        String message = "Session失效";
        return new SimpleResponse(message);
    }
}
