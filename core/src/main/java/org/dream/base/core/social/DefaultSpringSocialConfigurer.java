package org.dream.base.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 项目名称：security
 * 类 名 称：DefaultSpringSocialConfig
 * 类 描 述：
 * 创建时间：2020/3/22 21:47
 * 创 建 人：Lance.WU
 */
public class DefaultSpringSocialConfigurer extends SpringSocialConfigurer {

    /**
     * 处理URL请求的址
     */
    private String filterProcessorUrl;

    public DefaultSpringSocialConfigurer(String filterProcessorUrl) {
        this.filterProcessorUrl = filterProcessorUrl;
    }

    /**
     * 进行流程配置之前处理的类
     *
     * @param object 处理的配置对像
     * @param <T>    流程配置对像
     */
    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        // 放到过滤器的URL
        filter.setFilterProcessesUrl(filterProcessorUrl);
        return (T) filter;
    }
}
