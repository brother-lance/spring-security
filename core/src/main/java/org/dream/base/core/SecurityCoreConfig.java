package org.dream.base.core;

import org.dream.base.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 项目名称：security
 * 类 名 称：SecurityCoreConfig
 * 类 描 述：配置类
 * 创建时间：2020/3/19 12:21
 * 创 建 人：Lance.WU
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)  // 增加允许配置
public class SecurityCoreConfig {
}
