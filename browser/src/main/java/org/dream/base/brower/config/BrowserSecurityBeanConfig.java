package org.dream.base.brower.config;

import org.dream.base.brower.session.DefaultExpiredSessionStrategy;
import org.dream.base.brower.session.DefaultInvalidSessionStrategy;
import org.dream.base.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.annotation.Resource;

/**
 * 项目名称：security
 * 类 名 称：BrowserSecurityBeanConfig
 * 类 描 述：
 * 创建时间：2020/3/24 22:28
 * 创 建 人：Lance.WU
 */
@Configuration
public class BrowserSecurityBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy(){
        return new DefaultInvalidSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl()) {
        };
    }

    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
        return new DefaultExpiredSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl());
    }
}
