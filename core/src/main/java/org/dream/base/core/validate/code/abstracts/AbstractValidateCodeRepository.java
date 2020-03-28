package org.dream.base.core.validate.code.abstracts;

import org.dream.base.core.validate.code.ValidateCode;
import org.dream.base.core.validate.code.ValidateCodeType;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 项目名称：security
 * 类 名 称：AbstractValidateCodeRepository
 * 类 描 述：校验码的验证抽象类
 * 创建时间：2020/3/27 17:09
 * 创 建 人：Lance.WU
 */
public abstract class AbstractValidateCodeRepository<C extends ValidateCode> {

    protected abstract void save(ServletWebRequest request, ValidateCodeType type, C validateCode);

    protected abstract void remove(ServletWebRequest request, ValidateCodeType type);

    protected abstract C get(ServletWebRequest request, ValidateCodeType type);
}
