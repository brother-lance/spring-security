package org.dream.base.core.social.weixin.connect;

import org.dream.base.core.social.weixin.api.WeiXin;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * 项目名称：security
 * 类 名 称：WeiXinConnectionFactory
 * 类 描 述：微信连接工厂
 * 创建时间：2020/3/22 17:22
 * 创 建 人：Lance.WU
 */
public class WeiXinConnectionFactory extends OAuth2ConnectionFactory<WeiXin> {

    /**
     * 创建微信连接
     * @param appId
     * @param appSecret
     */
    public WeiXinConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new WeiXinServiceProvider(appId, appSecret), new WeiXinAdapter());
    }

    /**
     * 由于微信的openId是和accessToken一起返回的，所以在这里直接根据accessToken设置providerUserId即可，不用像QQ那样通过QQAdapter来获取
     */
    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
        if(accessGrant instanceof WeiXinAccessGrant) {
            return ((WeiXinAccessGrant)accessGrant).getOpenId();
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.springframework.social.connect.support.OAuth2ConnectionFactory#createConnection(org.springframework.social.oauth2.AccessGrant)
     */
    public Connection<WeiXin> createConnection(AccessGrant accessGrant) {
        return new OAuth2Connection<WeiXin>(getProviderId(), extractProviderUserId(accessGrant), accessGrant.getAccessToken(),
                accessGrant.getRefreshToken(), accessGrant.getExpireTime(), getOAuth2ServiceProvider(), getApiAdapter(extractProviderUserId(accessGrant)));
    }

    /* (non-Javadoc)
     * @see org.springframework.social.connect.support.OAuth2ConnectionFactory#createConnection(org.springframework.social.connect.ConnectionData)
     */
    public Connection<WeiXin> createConnection(ConnectionData data) {
        return new OAuth2Connection<WeiXin>(data, getOAuth2ServiceProvider(), getApiAdapter(data.getProviderUserId()));
    }

    private ApiAdapter<WeiXin> getApiAdapter(String providerUserId) {
        return new WeiXinAdapter(providerUserId);
    }

    private OAuth2ServiceProvider<WeiXin> getOAuth2ServiceProvider() {
        return (OAuth2ServiceProvider<WeiXin>) getServiceProvider();
    }

}
