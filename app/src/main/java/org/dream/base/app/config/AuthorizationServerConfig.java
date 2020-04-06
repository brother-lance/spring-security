package org.dream.base.app.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.dream.base.core.properties.Oauth2ClientProperties;
import org.dream.base.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 项目名称：security
 * 类 名 称：AuthorizationServerConfig
 * 类 描 述：自定义配置流程
 * 创建时间：2020/3/24 23:13
 * 创 建 人：Lance.WU
 */
@Slf4j
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    SecurityProperties securityProperties;

    @Autowired
    TokenStore tokenStore;

    @Autowired(required = false)
    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired(required = false)
    TokenEnhancer jwtTokenEnhancer;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore)
                .userDetailsService(userDetailsService);

        if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {

            TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancerList = new ArrayList<>();
            enhancerList.add(jwtTokenEnhancer);
            enhancerList.add(jwtAccessTokenConverter);
            enhancerChain.setTokenEnhancers(enhancerList);
            endpoints
                    .tokenEnhancer(enhancerChain)
                    .accessTokenConverter(jwtAccessTokenConverter);
        }


    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();

        log.info("{}", securityProperties.getOauth2().getClient());
        if (ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClient()))

            for (Oauth2ClientProperties oauth2ClientProperties : securityProperties.getOauth2().getClient()) {
                builder.withClient(oauth2ClientProperties.getClientId())
                        .secret(oauth2ClientProperties.getClientSecret())
                        .accessTokenValiditySeconds(oauth2ClientProperties.getAccessTokenValiditySeconds())
                        .refreshTokenValiditySeconds(1000 * 60 * 24 * 7)//  刷新Token的时间
                        .authorizedGrantTypes("refresh_token", "password")
                        .scopes("all", "read", "write")
                        .and();

            }


    }
}
