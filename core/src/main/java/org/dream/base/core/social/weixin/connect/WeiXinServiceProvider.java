package org.dream.base.core.social.weixin.connect;


import org.dream.base.core.social.weixin.api.WeiXin;
import org.dream.base.core.social.weixin.api.WeiXinImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * 项目名称：security
 * 类 名 称：WeiXinServiceProvider
 * 类 描 述：QQ服务提供者
 * 创建时间：2020/3/22 16:45
 * 创 建 人：Lance.WU
 */
public class WeiXinServiceProvider extends AbstractOAuth2ServiceProvider<WeiXin> {

    /**
     * 微信获取授权码的url
     */
    private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";
    /**
     * 微信获取accessToken的url
     */
    private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    /**
     * @param appId
     * @param appSecret
     */
    public WeiXinServiceProvider(String appId, String appSecret) {
        super(new WeiXinOAuth2Template(appId, appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
    }

    /* (non-Javadoc)
     * @see org.springframework.social.oauth2.AbstractOAuth2ServiceProvider#getApi(java.lang.String)
     */
    @Override
    public WeiXin getApi(String accessToken) {
        return new WeiXinImpl(accessToken);
    }

}
