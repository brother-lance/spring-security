package org.dream.base.core.validate.code.sms;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.RandomStringUtils;
import org.dream.base.core.properties.SecurityProperties;
import org.dream.base.core.validate.code.ValidateCode;
import org.dream.base.core.validate.code.ValidateCodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * 项目名称：security
 * 类 名 称：ImageValidateGene
 * 类 描 述：生成短信验证码
 * 创建时间：2020/3/19 22:49
 * 创 建 人：Lance.WU
 */
@Getter
@Setter
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {

    private  SecurityProperties securityProperties;

    @Override
    public ValidateCode generator(ServletWebRequest request) {
        String sRand = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(sRand, securityProperties.getCode().getSms().getExpireIn());
    }

}
