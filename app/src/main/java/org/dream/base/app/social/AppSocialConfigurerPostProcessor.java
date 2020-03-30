package org.dream.base.app.social;

import org.apache.commons.lang.StringUtils;
import org.dream.base.core.properties.SecurityProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.stereotype.Component;

/**
 * 项目名称：Security
 * 类 名 称：AppSocialConfigurerPostProcessor
 * 类 描 述：实现当前用户的配置信息，并进行替换
 * 创建时间：2020/3/30 09:35
 * 创 建 人：Lance.WU
 */
@Component
public class AppSocialConfigurerPostProcessor implements BeanPostProcessor {

    @Autowired
    SecurityProperties securityProperties;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        /**
         * 初始化之前什么也不做
         */
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        /**
         * 例如将当前对像初始化后进行改变
         */
        if(StringUtils.equals(beanName,"springSocialConfigurer")){
            SpringSocialConfigurer springSocialConfigurer =    (SpringSocialConfigurer)bean;
            springSocialConfigurer.signupUrl(securityProperties.getApp().getSignUpUrl());
        }

        return bean;
    }
}
