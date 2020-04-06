package org.dream.base.app.config;

import org.dream.base.app.jwt.DefaultJwtTokenEnhancer;
import org.dream.base.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.security.Security;

/**
 * 项目名称：security
 * 类 名 称：TokenStoreConfig
 * 类 描 述：token的存储配置
 * 创建时间：2020/4/6 10:33
 * 创 建 人：Lance.WU
 */
@Configuration
public class TokenStoreConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 下面的配置相反
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "security.oauth2",name="storeType",havingValue = "redis")
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * 当前配置文件中是security.oauth2.storeType 这个是属性的jwt时候是生效的
     * 当前如果没有配置这个属性的时候 ，是生效的
     */
    @Configuration
    @ConditionalOnProperty(prefix = "security.oauth2",name="storeType",havingValue = "jwt", matchIfMissing = true)
    public class JwtTokenConfig {

        @Autowired
        private SecurityProperties securityProperties;

        @Bean
        public JwtTokenStore jwtTokenStore(){
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter(){
            JwtAccessTokenConverter access = new JwtAccessTokenConverter();
            access.setSigningKey(securityProperties.getOauth2().getJwtSigningKey());
            return access;
        }

        @Bean
        @ConditionalOnMissingBean(name = "TokenEnhancer")
        public TokenEnhancer jwtTokenEnhancer(){
            return new DefaultJwtTokenEnhancer();
        }

    }

}
