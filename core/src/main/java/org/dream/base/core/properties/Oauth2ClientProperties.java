package org.dream.base.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 项目名称：security
 * 类 名 称：SocialProperties
 * 类 描 述：
 * 创建时间：2020/3/22 17:47
 * 创 建 人：Lance.WU
 */
@Getter
@Setter
public class Oauth2ClientProperties {


    private String clientId;

    private String clientSecret;

    private int  accessTokenValiditySeconds=7200;


}
