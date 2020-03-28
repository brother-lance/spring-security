package org.dream.base.brower.validate.code.impl;

import org.dream.base.core.properties.SecurityConstants;
import org.dream.base.core.validate.code.ValidateCode;
import org.dream.base.core.validate.code.ValidateCodeType;
import org.dream.base.core.validate.code.abstracts.AbstractValidateCodeRepository;
import org.dream.base.core.validate.code.image.ImageCode;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * 项目名称：security
 * 类 名 称：SessionValidateCodeRepository
 * 类 描 述：Session验证的实现
 * 创建时间：2020/3/27 17:21
 * 创 建 人：Lance.WU
 */
@Component
public class SessionValidateCodeRepository extends AbstractValidateCodeRepository {

    /**
     * 操作Session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void save(ServletWebRequest request, ValidateCodeType type, ValidateCode validateCode) {
        ValidateCode c = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
        sessionStrategy.setAttribute(request, getSessionKey(type), c);
    }

    @Override
    protected void remove(ServletWebRequest request, ValidateCodeType type) {
        sessionStrategy.removeAttribute(request, getSessionKey(type));

    }

    @Override
    protected ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
        return (ValidateCode) sessionStrategy.getAttribute(request, getSessionKey(type));
    }

    private String getSessionKey(ValidateCodeType type) {
        return SecurityConstants.DEFAULT_VALIDATE_SESSION_PREFIX + type.name().toUpperCase();
    }
}
