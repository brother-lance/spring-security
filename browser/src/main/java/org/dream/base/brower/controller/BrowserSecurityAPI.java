package org.dream.base.brower.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.dream.base.brower.support.SimpleResponse;
import org.dream.base.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
    /**
     * 跳转策略
     */
    RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    SecurityProperties securityProperties;

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
}
