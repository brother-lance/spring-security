package org.dream.base.core.validate.code;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 项目名称：security
 * 类 名 称：ValidateCodeProcessor
 * 类 描 述：校验码处理,封装不同的校验逻辑
 * 创建时间：2020/3/20 18:10
 * 创 建 人：Lance.WU
 */
public interface ValidateCodeProcessor {

    /**
     * 创建校验码
     * @param request 请求对像 （封装 request和response）
     * @throws Exception 异常信息
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 验证校验码
     * @param request 请求对像 （封装 request和response）
     * @throws ServletRequestBindingException
     */
    void validate(ServletWebRequest request) throws ServletRequestBindingException;


}
