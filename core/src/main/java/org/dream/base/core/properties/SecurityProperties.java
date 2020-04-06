package org.dream.base.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 项目名称：security
 * 类 名 称：SecurityProperties
 * 类 描 述：安全服务配置项
 * 创建时间：2020/3/19 12:12
 * 创 建 人：Lance.WU
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    private  SocialProperties social = new SocialProperties();

    private Oauth2Properties oauth2 = new Oauth2Properties();

    private AppProperties app = new AppProperties();


}
