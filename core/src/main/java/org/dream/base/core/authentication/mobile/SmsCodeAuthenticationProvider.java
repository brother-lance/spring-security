package org.dream.base.core.authentication.mobile;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 项目名称：security
 * 类 名 称：SmsCodeAuthenticationProvider
 * 类 描 述：
 * 创建时间：2020/3/20 23:47
 * 创 建 人：Lance.WU
 */
@Getter
@Setter
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    /**
     * @param authentication
     * @return 验证方法
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        SmsCodeAuthenticationToken smsCodeAuthenticationToken = (SmsCodeAuthenticationToken) authentication;
        UserDetails user = userDetailsService.loadUserByUsername((String) smsCodeAuthenticationToken.getPrincipal());

        if (null == user)
            throw new InternalAuthenticationServiceException("无法获取用户信息");

        SmsCodeAuthenticationToken smsCodeAuthenticationTokenResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());
        smsCodeAuthenticationTokenResult.setDetails(user);
        return smsCodeAuthenticationTokenResult;
    }

    /**
     * @param authentication 需要验证的Token类信息
     * @return 是否是需要判断的类。
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
