package org.dream.base.core.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 项目名称：security
 * 类 名 称：LoginType
 * 类 描 述：登录类型
 * 创建时间：2020/3/19 17:04
 * 创 建 人：Lance.WU
 */
@Getter
@AllArgsConstructor
public enum LoginType {

    JSON,
    REDIRECT,
    ;
}
