package org.dream.base.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * 项目名称：security
 * 类 名 称：DefaultConnectionSignUp
 * 类 描 述：默认连接注册页面
 * 创建时间：2020/3/23 00:07
 * 创 建 人：Lance.WU
 */
//@Component
public class DefaultConnectionSignUp implements ConnectionSignUp {

    @Override
    public String execute(Connection<?> connection) {

        //  TOOD 实现社交用户注册逻辑，并且 返用户唯一编号返回到,
        String  userId = connection.getDisplayName(); // 此处应该是 customer_no, user_id 等内容

        // connection 中为社交用户信息，头像，昵称 等内容。

        return userId;
    }
}
