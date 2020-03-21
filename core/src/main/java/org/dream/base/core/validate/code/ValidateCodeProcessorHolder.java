package org.dream.base.core.validate.code;

import lombok.Getter;
import lombok.Setter;

import org.dream.base.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 项目名称：security
 * 类 名 称：ValidateCodeProcessorHolder
 * 类 描 述：
 * 创建时间：2020/3/21 01:00
 * 创 建 人：Lance.WU
 */
@Getter
@Setter
@Component
public class ValidateCodeProcessorHolder {

    @Autowired
    Map<String, ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType validateCodeType) {
        return validateCodeProcessors.get(validateCodeType.getCode() + SecurityConstants.VALIDATE_CODE_PROCESSOR_SUFFIX);
    }

}
