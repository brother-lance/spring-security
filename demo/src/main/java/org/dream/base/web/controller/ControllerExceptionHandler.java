package org.dream.base.web.controller;

import org.dream.base.exception.UserNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称：security
 * 类 名 称：ControllerExceptionHandler
 * 类 描 述：控制器异常处理器
 * 创建时间：2020/3/18 11:07
 * 创 建 人：Lance.WU
 * 定义一个错误信息机制-  ControllerAdvice 拦截所有的抛出的异常,自定义
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotExistsException.class)  // 拦截哪些异常
    @ResponseBody // 返回客户端对像
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)// 返回对客户端的错误内容
    public Map<String, Object> handlerUserNotExistsException(UserNotExistsException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("id", ex.getId());
        error.put("message", ex.getMessage());
        return error;
    }

}
