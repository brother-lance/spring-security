package org.dream.base.core.validate.code;

import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.dream.base.core.properties.SecurityProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 项目名称：security
 * 类 名 称：ValidateCodeFilter
 * 类 描 述：图型验证码验证
 * 创建时间：2020/3/19 18:20
 * 创 建 人：Lance.WU
 */
@Setter
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    // 失败验证
    AuthenticationFailureHandler authenticationFailureHandler;

    // Session策略
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    // 安全配置
    SecurityProperties securityProperties;

    /**
     * 配置路径安工类
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    // 需要拦截的URL
    Set<String> urls = new HashSet<>();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] urlsStr = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(), ",");
        Arrays.asList(urlsStr).forEach(urls::add);
        urls.add("/authentication/form");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        AtomicBoolean action = new AtomicBoolean(false);
        urls.forEach(e -> {
            if (antPathMatcher.matchStart(e, request.getRequestURI()))
                action.set(true);

        });

        /** 判断当前是否是登录接口，并且是否是POST请求 */
//        if (StringUtils.endsWithIgnoreCase("/authentication/form", request.getRequestURI())
//                && StringUtils.endsWithIgnoreCase("post", request.getMethod())) {
        if (action.get()) {

            try {

                validate(new ServletWebRequest(request)); // 验证校验码

            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        // 进行下一步处理
        filterChain.doFilter(request, response);
    }

    /**
     * 验证认证码
     *
     * @param request
     */
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {

        // 得到验证码对像
        ImageCode imageCodeSession = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeAPI.SESSION_KEY);

        String imageCode = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

        if (StringUtils.isBlank(imageCode))
            throw new ValidateCodeException("验证码的值不能为空");

        if (imageCodeSession == null)
            throw new ValidateCodeException("验证码不存在");

        if (imageCodeSession.isExpired()) {
            sessionStrategy.removeAttribute(request, ValidateCodeAPI.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.endsWithIgnoreCase(imageCode, imageCodeSession.getCode())) {
            throw new ValidateCodeException("验证不匹配");
        }
        sessionStrategy.removeAttribute(request, ValidateCodeAPI.SESSION_KEY);
    }
}

