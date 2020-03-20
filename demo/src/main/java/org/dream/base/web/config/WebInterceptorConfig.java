package org.dream.base.web.config;

import org.dream.base.interceptor.TimeHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * 项目名称：security
 * 类 名 称：WebIntercetorConfig
 * 类 描 述：拦截器加载
 * 创建时间：2020/3/18 12:12
 * 创 建 人：Lance.WU
 */
@Configuration
public class WebInterceptorConfig extends WebMvcConfigurerAdapter {

    @Resource
    TimeHandler timeHandler;

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        //configurer.registerCallableInterceptors()  ;//配置 Callable异步请求的拦截器
        //configurer.registerDeferredResultInterceptors() 配置 DeferredResult异步请求的拦截器
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(timeHandler);
    }
}
