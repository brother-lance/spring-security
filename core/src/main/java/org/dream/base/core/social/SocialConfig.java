package org.dream.base.core.social;

import org.dream.base.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * 项目名称：security
 * 类 名 称：SocialConfig
 * 类 描 述：
 * 创建时间：2020/3/22 17:28
 * 创 建 人：Lance.WU
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    @Autowired(required = false)
    private SocialAuthenticationFilterPostProcess socialAuthenticationFilterPostProcess;

    /**
     * 配置认证信息存储至数据
     * @param connectionFactoryLocator
     * @return
     */
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository jdbcUsersConnectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        jdbcUsersConnectionRepository.setTablePrefix("t_");
        // 数据源、需要哪个连接、加解密(不加密)
        if(connectionSignUp !=null){
            jdbcUsersConnectionRepository.setConnectionSignUp(connectionSignUp);
        }
        return jdbcUsersConnectionRepository;
    }

    /**
     * 配置认证的流程，更改配置
     * @return
     */
    @Bean
    public SpringSocialConfigurer springSocialConfigurer() {
        // 创建配置信息，设置需要拦截到登录的信息
        DefaultSpringSocialConfigurer defaultSpringSocialConfigurer = new DefaultSpringSocialConfigurer(securityProperties.getSocial().getFilterProcessorUrl());
        defaultSpringSocialConfigurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
        defaultSpringSocialConfigurer.setSocialAuthenticationFilterPostProcess(socialAuthenticationFilterPostProcess);
        return defaultSpringSocialConfigurer;
    }

    /**
     * SpringSocial 注册工具类
     * @param connectionFactoryLocator
     * @return
     */
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator){
        return new ProviderSignInUtils(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator));
    }
}
