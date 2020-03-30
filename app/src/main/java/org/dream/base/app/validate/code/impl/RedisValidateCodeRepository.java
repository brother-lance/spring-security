package org.dream.base.app.validate.code.impl;

import org.dream.base.core.properties.SecurityProperties;
import org.dream.base.core.validate.code.ValidateCode;
import org.dream.base.core.validate.code.ValidateCodeType;
import org.dream.base.core.validate.code.abstracts.AbstractValidateCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;


/**
 * 项目名称：security
 * 类 名 称：SessionValidateCodeRepository
 * 类 描 述：Session验证的实现
 * 创建时间：2020/3/27 17:21
 * 创 建 人：Lance.WU
 */
@Component
public class RedisValidateCodeRepository extends AbstractValidateCodeRepository {

    /**
     * 操作Redis的工具类
     */
    @Autowired
    private RedisTemplate<Object, Object> template;

    @Autowired
    SecurityProperties securityProperties;

    @Override
    protected void save(ServletWebRequest request, ValidateCodeType type, ValidateCode validateCode) {
        ValidateCode c = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
        template.opsForValue().set(buildKey(request, type), c,
                securityProperties.getApp().getRedis().getCodeExpireTime(), TimeUnit.SECONDS);
    }

    @Override
    protected void remove(ServletWebRequest request, ValidateCodeType type) {
        template.delete(buildKey(request, type));
    }

    @Override
    protected ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
        Object o = template.opsForValue().get(buildKey(request, type));
        if (o == null) return null;
        return (ValidateCode) o;
    }

    private String buildKey(ServletWebRequest request, ValidateCodeType type) {
        String codeKey = request.getHeader(securityProperties.getApp().getRedis().getCodeKey());
        return "__CODE__" + type.name().toUpperCase() + codeKey;
    }
}
