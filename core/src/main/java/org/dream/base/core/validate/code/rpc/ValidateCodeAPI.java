package org.dream.base.core.validate.code.rpc;

import lombok.extern.slf4j.Slf4j;
import org.dream.base.core.properties.SecurityConstants;
import org.dream.base.core.validate.code.ValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 项目名称：security
 * 类 名 称：ValidateCodeAPI
 * 类 描 述：验证码验证服务
 * 创建时间：2020/3/19 18:03
 * 创 建 人：Lance.WU
 */
@Slf4j
@RestController
@RequestMapping("/api/1.0/code")
public class ValidateCodeAPI {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    @GetMapping("{type}")
    public void get(HttpServletRequest request, HttpServletResponse response, @PathVariable(required = true, value = "type") String type) throws Exception {
        validateCodeProcessors.get(type + SecurityConstants.VALIDATE_CODE_PROCESSOR_SUFFIX).create(new ServletWebRequest(request, response));
    }


}
