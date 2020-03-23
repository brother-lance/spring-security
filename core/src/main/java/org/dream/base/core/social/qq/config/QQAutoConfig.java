package org.dream.base.core.social.qq.config;

import org.dream.base.core.properties.QQProperties;
import org.dream.base.core.properties.SecurityProperties;
import org.dream.base.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * 项目名称：security
 * 类 名 称：QQAutoConfig
 * 类 描 述：QQ自动配置项
 * 创建时间：2020/3/22 18:06
 * 创 建 人：Lance.WU
 */
@Configuration
@ConditionalOnProperty(prefix = "security.social.qq", name = "appId")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {

        QQProperties qq = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qq.getProviderId(), qq.getAppId(), qq.getAppSecret());
    }
}
