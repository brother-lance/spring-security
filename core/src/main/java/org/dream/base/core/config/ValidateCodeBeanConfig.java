package org.dream.base.core.config;

import org.dream.base.core.properties.SecurityProperties;
import org.dream.base.core.validate.code.ImageCodeValidateGenerator;
import org.dream.base.core.validate.code.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 项目名称：security
 * 类 名 称：ValidateCodeBeanConfig
 * 类 描 述：验证码配置
 * 创建时间：2020/3/19 22:58
 * 创 建 人：Lance.WU
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeValidateGenerator generator = new ImageCodeValidateGenerator();
        generator.setSecurityProperties(securityProperties);
        return generator;
    }
}
