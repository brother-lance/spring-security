package org.dream.base.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * 项目名称：security
 * 类 名 称：QQProrperties
 * 类 描 述：
 * 创建时间：2020/3/22 17:44
 * 创 建 人：Lance.WU
 */
@Getter
@Setter
public class WeiXinProperties extends SocialProperties {

    private String providerId = "weixin";
}
