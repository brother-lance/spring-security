package org.dream.base.validator;

import lombok.extern.slf4j.Slf4j;
import org.dream.base.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 项目名称：security
 * 类 名 称：MyConstraintValidator
 * 类 描 述：验证接口
 * 创建时间：2020/3/18 10:12
 * 创 建 人：Lance.WU
 */
@Slf4j
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

    @Autowired
    HelloService helloService;

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        log.info("检验器初始化");
        System.out.println("初始化做的工作");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        log.info("执行我的校验器");
        log.info("Object:{}", value);
        log.info("context:{}", context);
        log.info("调用服务：{}", helloService.greeting("测试"));

        return false;
    }
}
