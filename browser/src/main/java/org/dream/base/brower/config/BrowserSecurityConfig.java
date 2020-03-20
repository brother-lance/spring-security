package org.dream.base.brower.config;

import org.dream.base.core.properties.SecurityProperties;
import org.dream.base.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * 项目名称：security
 * 类 名 称：BrowserSecurityConfig
 * 类 描 述：浏览器安全配置
 * 创建时间：2020/3/19 09:13
 * 创 建 人：Lance.WU
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

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
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private UserDetailsService userDetailsService;

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

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        // 关于Spring的安全验证登录。
        // 两种方式，一种是表单登录验证
        // 另外一种  弹出框验证
        /** 表单验证登录formLogin（），httpBasic（）弹出框验证登录 */
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)// 在登录验证之前添加一个是校验器
                .formLogin()
                //.loginPage("/signIn.html") /** 指定登录页面 */
                .loginPage("/authentication/require") /** 指定登录页面 */
                .loginProcessingUrl("/authentication/form") /** 验证登录的自定义方法 */
                .successHandler(authenticationSuccessHandler) // 成功之后返回
                .failureHandler(authenticationFailureHandler) // 失败之后返回
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository()) // 登录之后记录token
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds()) // token的有效时间配置
                .userDetailsService(userDetailsService) // 登录之后用户处理操作
                .and()
                /** 请求验证 */
                .authorizeRequests()
                //.antMatchers("/signIn.html").permitAll()  /** 过滤页面 */
                .antMatchers("/authentication/require", "/api/1.0/imageCode",
                        securityProperties.getBrowser().getLoginPage()).permitAll()  /** 过滤页面 */
                /** 任何地方都验证 */
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();  // 暂时关闭  攻击与防御
    }
}
