package org.dream.base.brower.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.dream.base.brower.support.SimpleResponse;
import org.dream.base.core.validate.code.LoginType;
import org.dream.base.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 项目名称：security
 * 类 名 称：AuthenticationFailHandlerImpl
 * 类 描 述：
 * 创建时间：2020/3/19 15:35
 * 创 建 人：Lance.WU
 */
@Slf4j
@Component
//public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler  // 默认配置 使用JSON
public class AuthenticationFailureHandlerImpl extends SimpleUrlAuthenticationFailureHandler {  // 可以使用重定向返回

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.info("登录失败");
        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            //response.getWriter().write(objectMapper.writeValueAsString(e)); // 返回错误信息，包括堆栈信息
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(e.getMessage())));// 反回简单错误信息

        } else

            super.onAuthenticationFailure(request, response, e);


    }
}
