package org.dream.base.app.social.openid;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.dream.base.app.exception.AppSecretException;
import org.dream.base.core.properties.SecurityConstants;
import org.dream.base.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.concurrent.TimeUnit;

/**
 * 项目名称：security
 * 类 名 称：AppSocialSignUpUtils
 * 类 描 述：
 * 创建时间：2020/3/30 09:15
 * 创 建 人：Lance.WU
 */
@Component
@Getter
@Setter
public class AppSocialSignUpUtils {

    @Autowired
    private RedisTemplate<Object, Object> template;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    @Autowired
    SecurityProperties securityProperties;

    private static final String DEFAULT_SECURITY_SOCIAL_CONNECT = "default.security.social.connect.";

    public void saveConnectionData(WebRequest request, ConnectionData connectionData) {
        // 1. 将社交登录的用户信息保存到redis中
        template.opsForValue().set(getKey(request), connectionData, 10, TimeUnit.MICROSECONDS);
    }

    public void doPostSignUp(WebRequest request, String userId) {
        // 1. 注册的时候，将根据Key(前缀加设备号)取出当前缓存的用户信息
        String key = getKey(request);
        if (!template.hasKey(key)) {
            throw new AppSecretException("无法获取当前缓存的用户社交账号信息");
        }
        // 2. 拿出当前缓存的用户信息，
        ConnectionData connectionData = (ConnectionData) template.opsForValue().get(key);
        // 3.根据当前缓存的信息根据提供者编号（如：微信、QQ等）获取social连接
        Connection connection = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId())
                .createConnection(connectionData);
        // 4.将用户编号与社交编号进行一个绑定
        usersConnectionRepository.createConnectionRepository(userId).addConnection(connection);
    }

    private String getKey(WebRequest request) {
        String deviceId = request.getParameter(securityProperties.getApp().getRedis().getCodeKey());
        if (StringUtils.isEmpty(deviceId)) {
            throw new AppSecretException("设备唯一编号不能为空");
        }
        return DEFAULT_SECURITY_SOCIAL_CONNECT + deviceId;
    }


}
