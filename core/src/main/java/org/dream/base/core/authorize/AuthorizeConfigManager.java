package org.dream.base.core.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * 项目名称：security
 * 类 名 称：AuthorizeConfigManager
 * 类 描 述：管理
 * 创建时间：2020/4/6 19:40
 * 创 建 人：Lance.WU
 */
public interface AuthorizeConfigManager {

    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry http);

}
