package org.dream.base.app.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 项目名称：security
 * 类 名 称：AppSecretException
 * 类 描 述：
 * 创建时间：2020/3/30 09:18
 * 创 建 人：Lance.WU
 */
@Getter
@Setter
public class AppSecretException extends  RuntimeException {

    private String message;

    public AppSecretException(String message){
        super(message);
        this.message = message;
    }
}
