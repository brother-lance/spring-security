package org.dream.base.service.impl;

import org.dream.base.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * 项目名称：security
 * 类 名 称：HelloServiceImpl
 * 类 描 述：实例类
 * 创建时间：2020/3/18 10:16
 * 创 建 人：Lance.WU
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String greeting(String userName) {
        return userName + " hello world!";
    }
}
