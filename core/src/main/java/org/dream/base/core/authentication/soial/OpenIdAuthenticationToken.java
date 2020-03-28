package org.dream.base.core.authentication.soial;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 项目名称：security
 * 类 名 称：OpenIdAuthenticationFilter
 * 类 描 述：
 * 创建时间：2020/3/28 18:04
 * 创 建 人：Lance.WU
 */
@Getter
@Setter
@Slf4j
public class OpenIdAuthenticationToken extends AbstractAuthenticationToken {

    private Object credentials;

    private Object principal;

    public OpenIdAuthenticationToken(String openId, String providerId) {
        super(null);
        this.credentials = providerId;
        this.principal = openId;
        setAuthenticated(false);
    }

    public OpenIdAuthenticationToken(UserDetails userDetails, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = userDetails;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
