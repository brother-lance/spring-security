package org.dream.base.core.social.weixin.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.nio.charset.Charset;
import java.util.List;


/**
 * 项目名称：security
 * 类 名 称：WeiXinImpl
 * 类 描 述：
 * 创建时间：2020/3/22 14:27
 * 创 建 人：Lance.WU
 * 此处因为Token的要求是不同的，需要多实例，不能进行单例模式
 */
@Slf4j
public class WeiXinImpl extends AbstractOAuth2ApiBinding implements WeiXin {

    private static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid=";


    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 业务类不加载配置信息 实现接口对像信息
     *
     * @param accessToken
     */
    public WeiXinImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);

    }

    /**
     * 默认注册的StringHttpMessageConverter字符集为ISO-8859-1，而微信返回的是UTF-8的，所以覆盖了原来的方法。
     */
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return messageConverters;
    }

    /**
     * 获取微信用户信息。
     */
    @Override
    public WeiXinUserInfo getWenXinUserInfo(String openId) {
        String url = URL_GET_USER_INFO + openId;
        String response = getRestTemplate().getForObject(url, String.class);
        if (StringUtils.contains(response, "errcode")) {
            return null;
        }
        WeiXinUserInfo profile = null;
        try {
            profile = objectMapper.readValue(response, WeiXinUserInfo.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return profile;
    }

}
