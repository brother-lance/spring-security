package org.dream.base.web.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目名称：security
 * 类 名 称：DefaultAPI
 * 类 描 述：默认API
 * 创建时间：2020/3/17 22:45
 * 创 建 人：Lance.WU
 */
@RestController
@RequestMapping("/api/1.0/default")
public class DefaultAPI {

    @GetMapping("/hello")
    public Object helloWorld() {
        return "hello";
    }
}
