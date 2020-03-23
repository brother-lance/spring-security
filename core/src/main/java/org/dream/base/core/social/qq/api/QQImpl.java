package org.dream.base.core.social.qq.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;


/**
 * 项目名称：security
 * 类 名 称：QQImpl
 * 类 描 述：
 * 创建时间：2020/3/22 14:27
 * 创 建 人：Lance.WU
 * 此处因为Token的要求是不同的，需要多实例，不能进行单例模式
 */
@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private static final String GET_OPEN_ID_URL = "https://graph.qq.com/oauth2.0/me?access_token=%s ";

    private static final String GET_USER_INFO_URL = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&=%s&openid=%s";

    private String appId;

    private String openId;

    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 业务类不加载配置信息 实现接口对像信息
     * @param accessToken
     * @param appId
     */
    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;

        String url = String.format(GET_OPEN_ID_URL, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);

        this.openId = StringUtils.substringBetween(result, "\"openid\":", "}");
    }

    /**
     * @See @{org.dream.base.core.social.qq.api.QQ#getQQUserInfo}
     * @return
     */
    @Override
    public QQUserInfo getQQUserInfo() {
        String url = String.format(GET_USER_INFO_URL, this.appId, this.openId);
        String result = getRestTemplate().getForObject(url, String.class);

        try {
            QQUserInfo userInfo = objectMapper.readValue(result, QQUserInfo.class);
            userInfo.setOpenId(this.openId);
            return userInfo;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
