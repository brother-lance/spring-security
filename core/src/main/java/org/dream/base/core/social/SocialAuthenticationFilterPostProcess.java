package org.dream.base.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * 项目名称：security
 * 类 名 称：SocialAuthenticationFilterPostProcess
 * 类 描 述：验证过滤器处理器
 * 创建时间：2020/3/28 21:24
 * 创 建 人：Lance.WU
 */
public interface SocialAuthenticationFilterPostProcess {

     void process(SocialAuthenticationFilter filter);
}
