package org.dream.base.core.validate.code.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 项目名称：security
 * 类 名 称：ValidateCodeException
 * 类 描 述：验证校验码异常
 * 创建时间：2020/3/19 18:28
 * 创 建 人：Lance.WU
 */

public class ValidateCodeException extends AuthenticationException {

    /**
     * 验证码异常
     * @param msg
     */
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
