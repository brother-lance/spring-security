package org.dream.base.core.social.qq.connect;

import org.dream.base.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * 项目名称：security
 * 类 名 称：QQConnectionFactory
 * 类 描 述：QQ连接工厂
 * 创建时间：2020/3/22 17:22
 * 创 建 人：Lance.WU
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    /**
     * 获取数据连接内容
     *
     * @param providerId
     * @param appId
     * @param appSecret
     */
    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }

}
