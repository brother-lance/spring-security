package org.dream.base.app.authentication.socil.openid;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 项目名称：security
 * 类 名 称：OpenIdAuthenticationFilter
 * 类 描 述：
 * 创建时间：2020/3/28 18:04
 * 创 建 人：Lance.WU
 */
@Getter
@Setter
public class OpenIdAuthenticationProvider implements AuthenticationProvider {

    private SocialUserDetailsService socialUserDetailsService;

    private UsersConnectionRepository usersConnectionRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        /** 验证用户信息 */
        OpenIdAuthenticationToken authenticationToken = (OpenIdAuthenticationToken) authentication;

        Set<String> providerUserIds = new HashSet<>();
        providerUserIds.add(authenticationToken.getPrincipal().toString());
        Set<String> userIds = usersConnectionRepository.
                findUserIdsConnectedTo(authenticationToken.getCredentials().toString(), providerUserIds);

        if (CollectionUtils.isEmpty(userIds) || userIds.size() != 1)
            throw new InternalAuthenticationServiceException("无法获取用户信息");

        String userId = userIds.iterator().next();

        UserDetails userDetails = socialUserDetailsService.loadUserByUserId(userId);

        OpenIdAuthenticationToken openIdAuthenticationTokenResult =
                new OpenIdAuthenticationToken(userDetails, userDetails.getAuthorities());

        return openIdAuthenticationTokenResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OpenIdAuthenticationToken.class.isAssignableFrom(authentication);

    }
}
