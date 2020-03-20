package org.dream.base.web.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目名称：security
 * 类 名 称：LoginAPI
 * 类 描 述：登录信息
 * 创建时间：2020/3/19 17:46
 * 创 建 人：Lance.WU
 */
@RestController
@Slf4j
@RequestMapping("/api/1.0/login")
public class LoginAPI {

    /**
     * 方式1 返回登录之后的用户信息，全部认证信息-通过对像获取
     * @return
     */
    @GetMapping("1")
    public Object getLoginInfp1() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 方式2 返回登录之后的用户信息，全部认证信息-直接对像IOC获取
     * @return
     */
    @GetMapping("2")
    public Object getLoginInfp2(Authentication authentication) {
        return authentication;
    }

    /**
     * 获取用户的人详细信息
     * @param userDetails
     * @return
     */
    @GetMapping
    public Object getLoginInfp2(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

}
