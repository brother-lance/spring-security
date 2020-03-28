package org.dream.base.app.authentication.socil.openid;

import org.apache.commons.lang.StringUtils;
import org.dream.base.core.properties.SecurityConstants;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 项目名称：security
 * 类 名 称：OpenIdAuthenticationFilter
 * 类 描 述：
 * 创建时间：2020/3/28 18:04
 * 创 建 人：Lance.WU
 */
public class OpenIdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private String openIdParameter = SecurityConstants.DEFAULT_SOCIAL_PARAMETER_NAME_OPEN_ID;
    private String providerIdParameter = SecurityConstants.DEFAULT_SOCIAL_PARAMETER_NAME_PROVIDER_ID;
    private boolean postOnly = true;

    public OpenIdAuthenticationFilter() {
        super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_OPENID, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if (postOnly && !request.getMethod().equalsIgnoreCase("POST"))
            throw new AuthenticationServiceException("Authentication method not supperted:" + request.getMethod());

        String openId = obtainParameter(request,openIdParameter,"");
        String providerId = obtainParameter(request, providerIdParameter,"");

        OpenIdAuthenticationToken openIdAuthenticationToken = new OpenIdAuthenticationToken(openId, providerId);

        setDetails(request, openIdAuthenticationToken);

        return this.getAuthenticationManager().authenticate(openIdAuthenticationToken);
    }

    private String obtainParameter(HttpServletRequest request, String key, String defaultValue) {
        String parameter = new ServletWebRequest(request).getParameter(key);
        if (StringUtils.isNotEmpty(parameter)) return parameter.trim();
        return defaultValue.trim();
    }

    protected void setDetails(HttpServletRequest request, OpenIdAuthenticationToken openIdAuthenticationToken){

        openIdAuthenticationToken.setDetails(authenticationDetailsSource.buildDetails(request));

    }


}
