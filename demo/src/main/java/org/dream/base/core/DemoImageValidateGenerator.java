package org.dream.base.core;

import lombok.extern.slf4j.Slf4j;
import org.dream.base.core.validate.code.ImageCode;
import org.dream.base.core.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 项目名称：security
 * 类 名 称：DemoImageValidateGenerator
 * 类 描 述：测试生成更高级的验证码
 * 创建时间：2020/3/19 23:09
 * 创 建 人：Lance.WU
 */
@Slf4j
//@Component(value = "imageCodeGenerator")
public class DemoImageValidateGenerator implements ValidateCodeGenerator {

    @Override
    public ImageCode generator(ServletWebRequest request) {
        log.info("生成图型验证码.");
        return null;
    }
}
