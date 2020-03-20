package org.dream.base.web.config;

import org.dream.base.web.filter.TimeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：security
 * 类 名 称：WebConfig
 * 类 描 述：前端配置-配置第三方Jar包
 * 创建时间：2020/3/18 11:53
 * 创 建 人：Lance.WU
 */
@Configuration
public class WebConfig  {

    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(); // 创建一个过滤器是注册器

        TimeFilter timeFilter = new TimeFilter();  // 创建一个第三方过滤器jar
        filterRegistrationBean.setFilter(timeFilter); // 添加到注册器中
        // 添加拦截配置
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        filterRegistrationBean.setUrlPatterns(urls);
        return filterRegistrationBean;
    }

}
