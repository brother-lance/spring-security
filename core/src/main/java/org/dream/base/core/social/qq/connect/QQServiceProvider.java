package org.dream.base.core.social.qq.connect;


import org.dream.base.core.social.qq.api.QQ;
import org.dream.base.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * 项目名称：security
 * 类 名 称：QQServiceProvider
 * 类 描 述：QQ服务提供者
 * 创建时间：2020/3/22 16:45
 * 创 建 人：Lance.WU
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;

    private static final String AUTHORIZE_URL = "https://graph.qq.com/oauth2.0/authorize";
    private static final String ACCESS_TOKEN_URL = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret) {
        super(new QQOAuth2Template(appId, appSecret, AUTHORIZE_URL, ACCESS_TOKEN_URL));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }
}
