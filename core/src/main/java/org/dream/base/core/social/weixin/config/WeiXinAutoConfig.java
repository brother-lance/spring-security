package org.dream.base.core.social.weixin.config;

import org.dream.base.core.properties.SecurityProperties;
import org.dream.base.core.properties.WeiXinProperties;
import org.dream.base.core.social.weixin.connect.WeiXinConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * 项目名称：security
 * 类 名 称：WeiXinAutoConfig
 * 类 描 述：微信自动配置项
 * 创建时间：2020/3/22 18:06
 * 创 建 人：Lance.WU
 */
@Configuration
@ConditionalOnProperty(prefix = "security.social.weixin", name = "appId")
public class WeiXinAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {

        WeiXinProperties weixin = securityProperties.getSocial().getWeixin();
        return new WeiXinConnectionFactory(weixin.getProviderId(), weixin.getAppId(), weixin.getAppSecret());
    }

}
