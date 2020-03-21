package org.dream.base.core.validate.code.image;

import lombok.Getter;
import lombok.Setter;
import org.dream.base.core.properties.SecurityProperties;
import org.dream.base.core.impl.AbstractValidateCodeProcessor;
import org.dream.base.core.validate.code.ValidateCode;
import org.dream.base.core.validate.code.ValidateCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * 项目名称：security
 * 类 名 称：ImageValidateCodeProcessor
 * 类 描 述：
 * 创建时间：2020/3/20 18:34
 * 创 建 人：Lance.WU
 */
@Component("imageCodeProcessor")
@Getter
@Setter
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    @Autowired
    private SecurityProperties securityProperties;

    /***
     * 发送图型验证码
     * @see AbstractValidateCodeProcessor#send(ServletWebRequest, ValidateCode)
     */
    @Override
    public void send(ServletWebRequest request, ImageCode validateCode) throws Exception {
        ImageIO.write(validateCode.getImage(), securityProperties.getCode().getImage().getFormat(), request.getResponse().getOutputStream());
    }

    /**
     * 验证认证码
     *
     * @param request 请求对象
     */
    @Override
    public void validate(ServletWebRequest request) throws ServletRequestBindingException {
        super.validate(request, ValidateCodeType.IMAGE,securityProperties.getCode().getImage().getCodeInputName());
    }
}
