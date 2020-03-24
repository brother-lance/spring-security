package org.dream.base.brower.support;

import lombok.Getter;
import lombok.Setter;

/**
 * 项目名称：security
 * 类 名 称：SocialUserIfnfo
 * 类 描 述：社交用户信息
 * 创建时间：2020/3/22 23:52
 * 创 建 人：Lance.WU
 */
@Getter
@Setter
public class SocialUserInfo {

    private String providerId;

    private String providerUserId;

    private String nickname;

    private String headimg;

}
