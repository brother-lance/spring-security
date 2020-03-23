package org.dream.base.brower.config;

import org.dream.base.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import org.dream.base.core.properties.SecurityConstants;
import org.dream.base.core.properties.SecurityProperties;
import org.dream.base.core.validate.code.config.AbstractChannelSecurityConfig;
import org.dream.base.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * 项目名称：security
 * 类 名 称：BrowserSecurityConfig
 * 类 描 述：浏览器安全配置
 * 创建时间：2020/3/19 09:13
 * 创 建 人：Lance.WU
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    /**
     * 配置密码加密逻辑
     *
     * @return 加密对像
     */
    @Bean
    public PasswordEncoder configurePassEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    SpringSocialConfigurer springSocialConfigurer;

    @Autowired
    private DataSource dataSource;

    /**
     * 生成记录的Token的数据库对像
     *
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();  // 配置JDBC 数据源 ，通过重写配置数据源，可以使用redis
        tokenRepository.setDataSource(dataSource);
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    /**
     * 配置登录验证流程的逻辑信息
     *
     * @param http http流程对像
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 添加登录的配置
        applyPasswordAuthenticationConfig(http);

        // 浏览器相关的配置信息
        http.apply(validateCodeSecurityConfig) // 添加验证码配置
                .and()
                .apply(smsCodeAuthenticationSecurityConfig) // 添加短信配置
                .and()
                .apply(springSocialConfigurer)  // 社交登录的配置
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository()) // 配置token存储数据源
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds()) // 登录保存时间配置
                .userDetailsService(userDetailsService) // 获取用户信息服务
                .and()
                .authorizeRequests() // 验证相关信息
                .antMatchers( //过滤器相关配置
                        SecurityConstants.DEFAULT_UN_AUTHENTICATION_URL,  // 不需要授权页面(判断登录页)
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, // 手机号登录页面
                        securityProperties.getBrowser().getLoginPage(), //登录页配置
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*" ,// 验证流程的页面.
                        securityProperties.getBrowser().getSignUpUrl() , // 注册地址
                        "/api/1.0/user/register" // TODO  如果需要跳转页面，可以偷偷注册
                ).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();  // 暂时关闭  攻击与防御


        //ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
//        validateCodeFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
//        validateCodeFilter.setSecurityProperties(securityProperties);
//        validateCodeFilter.afterPropertiesSet();

//        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
//        smsCodeFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
//        smsCodeFilter.setSecurityProperties(securityProperties);
//        smsCodeFilter.afterPropertiesSet();

        // 关于Spring的安全验证登录。
        // 两种方式，一种是表单登录验证
        // 另外一种  弹出框验证


//        /** 表单验证登录formLogin（），httpBasic（）弹出框验证登录 */
//        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class).
//                addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)// 在登录验证之前添加一个是校验器
//                .formLogin()
//                //.loginPage("/signIn.html") /** 指定登录页面 */
//                .loginPage("/authentication/require") /** 判断前往登录页面 */
//                .loginProcessingUrl("/authentication/form") /** 验证登录的自定义方法 */
//                .successHandler(authenticationSuccessHandler) // 成功之后返回
//                .failureHandler(authenticationFailureHandler) // 失败之后返回
//                .and()
//                .rememberMe()
//                .tokenRepository(persistentTokenRepository()) // 登录之后记录token
//                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds()) // token的有效时间配置
//                .userDetailsService(userDetailsService) // 登录之后用户处理操作
//                .and()
//                /** 请求验证 */
//                .authorizeRequests()
//                //.antMatchers("/signIn.html").permitAll()  /** 过滤页面 */
//                .antMatchers("/authentication/require",
//                        securityProperties.getBrowser().getLoginPage(),
//                        securityProperties.getCode().getGeneratorValidateCodeUrlPrefix() + "/*").permitAll()  /** 过滤页面 */
//                /** 任何地方都验证 */
//                .anyRequest()
//                .authenticated()
//                .and()
//                .csrf().disable()  // 暂时关闭  攻击与防御
//                .apply(smsCodeAuthenticationSecurityConfig); // 添加新的配置信息
    }


}
