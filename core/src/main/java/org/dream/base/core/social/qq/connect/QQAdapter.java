package org.dream.base.core.social.qq.connect;

import lombok.extern.slf4j.Slf4j;
import org.dream.base.core.social.qq.api.QQ;
import org.dream.base.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * 项目名称：security
 * 类 名 称：QQAdapter
 * 类 描 述：QQ适配器
 * 创建时间：2020/3/22 17:04
 * 创 建 人：Lance.WU
 */
@Slf4j
public class QQAdapter implements ApiAdapter<QQ> {

    /**
     * 测试API是否可用
     *
     * @param api
     * @return
     */
    @Override
    public boolean test(QQ api) {
        return true;
    }

    /**
     * 获取个人信息
     *
     * @param api
     * @param values
     */
    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getQQUserInfo();
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        values.setProfileUrl(null);// QQ用户信息主页
        values.setProviderUserId(userInfo.getOpenId());

    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {
        // 如果存在个人网页，则可以通过此处
    }
}
