package org.dream.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 项目名称：security
 * 类 名 称：DemoApplication
 * 类 描 述：
 * 创建时间：2020/3/17 22:44
 * 创 建 人：Lance.WU
 */
@SpringBootApplication
@EnableSwagger2
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
