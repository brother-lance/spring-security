package org.dream.base.core.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 项目名称：security
 * 类 名 称：DefaultAuthorizeConfigManager
 * 类 描 述：
 * 创建时间：2020/4/6 19:41
 * 创 建 人：Lance.WU
 */
@Component
public class DefaultAuthorizeConfigManager implements  AuthorizeConfigManager {


    @Autowired
    private Set<AuthorizeConfigProvider> authorizeConfigProviders;


    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry http) {

        for (AuthorizeConfigProvider authorizeConfigProvider:authorizeConfigProviders){
            authorizeConfigProvider.config(http);
        }
        http.anyRequest().authenticated();
    }
}
