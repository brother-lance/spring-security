package org.dream.base.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 项目名称：security
 * 类 名 称：UserNotExistsException
 * 类 描 述：用户找不到异常
 * 创建时间：2020/3/18 10:59
 * 创 建 人：Lance.WU
 */
@Getter
@Setter
public class UserNotExistsException extends  RuntimeException {

    private String id;

    public  UserNotExistsException(String id){
        super("user not exists");
        this.id = id;
    }

}
