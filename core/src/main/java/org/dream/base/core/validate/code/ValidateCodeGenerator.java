package org.dream.base.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 项目名称：security
 * 类 名 称：ValidateCodeGenerator
 * 类 描 述：实现短信验证接口
 * 创建时间：2020/3/19 22:50
 * 创 建 人：Lance.WU
 */
public interface ValidateCodeGenerator {

    /**
     * 生成验证码
     * @param request 请求参数
     * @return 验证码
     */
    ValidateCode generator(ServletWebRequest request);
}
