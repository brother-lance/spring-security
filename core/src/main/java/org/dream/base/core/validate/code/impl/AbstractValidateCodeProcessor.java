package org.dream.base.core.validate.code.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.dream.base.core.properties.SecurityConstants;
import org.dream.base.core.validate.code.ValidateCode;
import org.dream.base.core.validate.code.ValidateCodeGenerator;
import org.dream.base.core.validate.code.ValidateCodeProcessor;
import org.dream.base.core.validate.code.ValidateCodeType;
import org.dream.base.core.validate.code.exception.ValidateCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * 项目名称：security
 * 类 名 称：AbstractValidateCodeProcessor
 * 类 描 述：校验的处理实现类
 * 创建时间：2020/3/20 18:23
 * 创 建 人：Lance.WU
 */
@Slf4j
@Getter
@Setter
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    /**
     * 操作Session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 收集系统中所有的 {@link ValidateCodeGenerator} 的接口实现
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    /**
     * @see org.dream.base.core.validate.code.ValidateCodeProcessor#create(ServletWebRequest request)
     */
    @Override
    public void create(ServletWebRequest request) throws Exception {

        C validateCode = Generator(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    /**
     * 生成校验码
     *
     * @param request 请求对象
     * @return 验证码
     */
    @SuppressWarnings("unchecked")
    public C Generator(ServletWebRequest request) {

        String type = getProcessorType(request);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(type + SecurityConstants.VALIDATE_CODE_GENERATOR_SUFFIX);
        return (C) validateCodeGenerator.generator(request);
    }

    /**
     * 获到操作类型
     * 例如：
     * 请求验证码的地址是 /api/1.0/code/image 通过前缀获取到  image
     *
     * @param request 请求对像
     */
    public String getProcessorType(ServletWebRequest request) {

        return StringUtils.substringAfter(request.getRequest().getRequestURI(), SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX);
    }

    /**
     * @param request      请求对像
     * @param validateCode 保存验证码
     */
    public void save(ServletWebRequest request, C validateCode) {

        sessionStrategy.setAttribute(request, SecurityConstants.DEFAULT_VALIDATE_SESSION_PREFIX + getProcessorType(request).toUpperCase(), validateCode);
    }

    /**
     * 验证认证码
     *
     * @param request 请求对偈
     */
    public void validate(ServletWebRequest request, ValidateCodeType validateCodeType, String codeInputNameKey) throws ServletRequestBindingException {

        String key = SecurityConstants.DEFAULT_VALIDATE_SESSION_PREFIX + validateCodeType.name().toUpperCase();

        // 得到验证码对象
        ValidateCode validateCodeSession = (ValidateCode) sessionStrategy.getAttribute(request, key);

        String validateCode = ServletRequestUtils.getStringParameter(request.getRequest(), codeInputNameKey);

        if (StringUtils.isBlank(validateCode))
            throw new ValidateCodeException("验证码的值不能为空");

        if (validateCodeSession == null)
            throw new ValidateCodeException("验证码不存在");

        if (validateCodeSession.isExpired()) {
            sessionStrategy.removeAttribute(request, key);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.endsWithIgnoreCase(validateCode, validateCodeSession.getCode())) {
            throw new ValidateCodeException("验证不匹配");
        }
        sessionStrategy.removeAttribute(request, key);
    }

    /**
     * 发送验证码
     *
     * @param request      发送请求，工具类
     * @param validateCode 校验码
     * @throws Exception 失败异常
     */
    public abstract void send(ServletWebRequest request, C validateCode) throws Exception;

}
