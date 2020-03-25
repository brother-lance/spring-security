package org.dream.base.core.validate.code;
//
//import lombok.Getter;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.dream.base.core.properties.SecurityConstants;
//import org.dream.base.core.properties.SecurityProperties;
//import org.dream.base.core.validate.code.exception.ValidateCodeException;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.social.connect.web.HttpSessionSessionStrategy;
//import org.springframework.social.connect.web.SessionStrategy;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.web.bind.ServletRequestBindingException;
//import org.springframework.web.bind.ServletRequestUtils;
//import org.springframework.web.context.request.ServletWebRequest;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.*;
//import java.util.concurrent.atomic.AtomicBoolean;
//
///**
// * 项目名称：security
// * 类 名 称：ValidateCodeFilter
// * 类 描 述：图型验证码验证
// * 创建时间：2020/3/19 18:20
// * 创 建 人：Lance.WU
// */
//@Slf4j
//@Setter
//@Getter
//@Component("validateCodeFilter")
//public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {
//
//
//    /**
//     * 验证码失败验证处理器
//     */
//    @Autowired
//    private AuthenticationFailureHandler authenticationFailureHandler;
//
//    /**
//     * 系统配置信息
//     */
//    @Autowired
//    private SecurityProperties securityProperties;
//
//    /**
//     * 系统验证码验证处理器
//     */
//    @Autowired
//    private ValidateCodeProcessorHolder validateCodeProcessorHolder;
//
//    /**
//     * 存放所需要验证码的处理器
//     */
//    Map<String, ValidateCodeType> urlMap = new HashMap<>();
//
//    // Session策略
//    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
//
//    /**
//     * 配置路径安工类
//     */
//    private AntPathMatcher antPathMatcher = new AntPathMatcher();
//
//    // 需要拦截的URL
//    Set<String> urls = new HashSet<>();
//
//    @Override
//    public void afterPropertiesSet() throws ServletException {
//        super.afterPropertiesSet();
//        String[] urlsStr = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(), ",");
//        Arrays.asList(urlsStr).forEach(urls::add);
//        urls.add("/authentication/form");
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        AtomicBoolean action = new AtomicBoolean(false);
//        urls.forEach(e -> {
//            if (antPathMatcher.matchStart(e, request.getRequestURI()))
//                action.set(true);
//
//        });
//
//        /** 判断当前是否是登录接口，并且是否是POST请求 */
//        if (action.get()) {
//
//            try {
//                validate(new ServletWebRequest(request)); // 验证校验码
//
//            } catch (ValidateCodeException e) {
//                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
//                return;
//            }
//        }
//        // 进行下一步处理
//        filterChain.doFilter(request, response);
//    }
//
//    /**
//     * 验证认证码
//     *
//     * @param request
//     */
//    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
//
//        String key = SecurityConstants.DEFAULT_VALIDATE_SESSION_PREFIX + "image".toUpperCase();
//
//        // 得到验证码对象
//        ValidateCode validateCodeSession = (ValidateCode) sessionStrategy.getAttribute(request, key);
//
//        String validateCode = ServletRequestUtils.getStringParameter(request.getRequest(), securityProperties.getCode().getImage().getCodeInputNameKey());
//
//        if (StringUtils.isBlank(validateCode))
//            throw new ValidateCodeException("验证码的值不能为空");
//
//        if (validateCodeSession == null)
//            throw new ValidateCodeException("验证码不存在");
//
//        if (validateCodeSession.isExpired()) {
//            sessionStrategy.removeAttribute(request, key);
//            throw new ValidateCodeException("验证码已过期");
//        }
//
//        if (!StringUtils.endsWithIgnoreCase(validateCode, validateCodeSession.getCode())) {
//            throw new ValidateCodeException("验证不匹配");
//        }
//        sessionStrategy.removeAttribute(request, key);
//    }
//}
//


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.dream.base.core.properties.SecurityConstants;
import org.dream.base.core.properties.SecurityProperties;
import org.dream.base.core.validate.code.exception.ValidateCodeException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Getter
@Setter
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    /**
     * 校验码流程持有者
     */
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 安全配置属性文件
     */
    @Autowired
    private SecurityProperties securityProperties;
    /**
     * 处理失败对像
     */
    @Autowired
    AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 配置路径安工类
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 存放所需要验证码的处理器
     */
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        addUrl(ValidateCodeType.IMAGE, SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, securityProperties.getCode().getImage().getUrl());

        addUrl(ValidateCodeType.SMS, SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, securityProperties.getCode().getSms().getUrl());
    }

    // 解析需要拦截的URL
    private void addUrl(ValidateCodeType validateCodeType, String loginUrl, String filterUrl) {
        urlMap.put(loginUrl, validateCodeType);
        addToMap(filterUrl, validateCodeType);

    }

    // 将URL转换单个URL 添加需要验证的MAP中
    private void addToMap(String url, ValidateCodeType image) {
        String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(url, ",");
        Arrays.asList(urls).forEach(u -> {
            urlMap.put(u, image);
        });
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        // 1.获取验证匹配类型
        ValidateCodeType type = getValidateCodeType(request);

        if (type != null) {
            log.debug("校验请求：(" + request.getRequestURI() + ")中的校验码，校验码类型：" + type.name());
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type)
                        .validate(new ServletWebRequest(request, response));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    /**
     * 获取校验码类型
     *
     * @param request 请求对像
     * @return 校验码类型
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {

        Optional<Map.Entry<String, ValidateCodeType>> optional = urlMap.entrySet().stream().filter(e -> antPathMatcher.matchStart(e.getKey(),
                request.getRequestURI())).findFirst();

        if (!optional.isPresent())
            return null;

        return optional.get().getValue();

    }


}