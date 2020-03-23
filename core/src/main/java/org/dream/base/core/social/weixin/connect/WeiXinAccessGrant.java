package org.dream.base.core.social.weixin.connect;

import lombok.Getter;
import lombok.Setter;
import org.springframework.social.oauth2.AccessGrant;

/**
 * 项目名称：security
 * 类 名 称：WeiXinAccessGrant
 * 类 描 述：
 * 创建时间：2020/3/23 00:47
 * 创 建 人：Lance.WU
 */
@Getter
@Setter
public class WeiXinAccessGrant extends AccessGrant {

    private String openId;

    public WeiXinAccessGrant() {
        super("");
    }

    public WeiXinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }

}
