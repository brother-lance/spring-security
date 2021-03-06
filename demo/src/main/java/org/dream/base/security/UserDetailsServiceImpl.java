package org.dream.base.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 项目名称：security
 * 类 名 称：UserService
 * 类 描 述：用户服务-验证用户账号
 * 创建时间：2020/3/19 09:42
 * 创 建 人：Lance.WU
 * 1. 实现Spring的业务逻辑
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService, SocialUserDetailsService {

    /**
     * 密码加密对象
     */
    @Resource
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("登录用户名:{}", userName);
        // 用户名、密码、权限列表  可以通过数据库录入


        return buildUser(userName);

    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        log.info("社交登录用户:{}", userId);
        return buildUser(userId);

    }

    private SocialUserDetails buildUser(String userId) {


        // TODO 此处需要用户自己实现 查询用户信息的内容
        String encode = passwordEncoder.encode("123456");
        /**
         *  enabled: 一般是指用户的是否删除。
         *  accountNonExpired: 账号是否过期。
         *  credentialsNonExpired: 密码是否过期
         *  accountNonLocked: 是否锁定
         * */
        /** 实际业务逻辑，应该是直接从数据库中读取数据 */
        return new SocialUser(userId, encode,
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
    }
}
