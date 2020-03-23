package org.dream.base.core.social.qq.connect;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * 项目名称：security
 * 类 名 称：QQOauth2Template
 * 类 描 述：
 * 创建时间：2020/3/22 22:51
 * 创 建 人：Lance.WU
 */
public class QQOAuth2Template extends OAuth2Template {

    private static final String SEPARATOR = "=";

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }

    /**
     * 重写发送QQ的内容 转换SpringOauth2相要的结果
     *
     * @param accessTokenUrl 请求TOKENURL
     * @param parameters     参数
     */
    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String responseStr = this.getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);

        String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr, "&");

        String accessToken = StringUtils.substringAfter(items[0], SEPARATOR);
        long expiresIn = Long.valueOf(StringUtils.substringAfter(items[1], SEPARATOR));
        String refreshToken = StringUtils.substringAfter(items[2], SEPARATOR);

        return new AccessGrant(accessToken, null, refreshToken, expiresIn);
    }

    /**
     * 重写复类，添加返回http格式的响应内容
     *
     * @return RestTemplate
     */
    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }
}
