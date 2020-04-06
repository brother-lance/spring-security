package org.dream.base.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 项目名称：security
 * 类 名 称：Oauth2Properties
 * 类 描 述：
 * 创建时间：2020/4/6 10:25
 * 创 建 人：Lance.WU
 */
@Getter
@Setter
public class Oauth2Properties {

    private String jwtSigningKey = "default";

    Oauth2ClientProperties client[] ={};
}
