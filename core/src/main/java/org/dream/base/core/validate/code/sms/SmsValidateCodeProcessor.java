package org.dream.base.core.validate.code.sms;

import lombok.Getter;
import lombok.Setter;
import org.dream.base.core.authentication.mobile.SmsCodeSender;
import org.dream.base.core.properties.SecurityProperties;
import org.dream.base.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.dream.base.core.validate.code.ValidateCode;
import org.dream.base.core.validate.code.ValidateCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 项目名称：security
 * 类 名 称：ImageValidateCodeProcessor
 * 类 描 述：短信验证码生成
 * 创建时间：2020/3/20 18:34
 */
@Component("smsCodeProcessor")
@Getter
@Setter
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private SmsCodeSender smsCodeSender;

    /***
     * 发送短信验证码
     * @see @{AbstractValidateCodeProcessor#send(ServletWebRequest, ValidateCode)}
     */
    @Override
    public void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), securityProperties.getCode().getSms().getMobileInputName());
        smsCodeSender.send(mobile, validateCode.getCode());
    }

    /**
     * 验证认证码
     *
     * @param request 请求对象
     */
    @Override
    public void validate(ServletWebRequest request) throws ServletRequestBindingException {
        super.validate(request, ValidateCodeType.SMS,securityProperties.getCode().getSms().getCodeInputName());

    }
}
