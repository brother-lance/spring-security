package org.dream.base.core.validate.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Resource
    private ValidateCodeGenerator validateCodeGenerator;

    @GetMapping("{type}")
    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ImageCode imageCode = validateCodeGenerator.generator(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());

    }


}
