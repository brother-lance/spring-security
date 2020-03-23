package org.dream.base.core.social.weixin.api;

/**
 * 项目名称：security
 * 类 名 称：WeiXin
 * 类 描 述：
 * 创建时间：2020/3/22 14:26
 * 创 建 人：Lance.WU
 */
public interface WeiXin {

    /**
     * 获取用户信息API
     * @return
     */
    WeiXinUserInfo getWenXinUserInfo(String openId);
}
