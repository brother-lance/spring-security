package org.dream.base.core.properties;

/**
 * 项目名称：security
 * 类 名 称：SecurityConstants
 * 类 描 述：安全验证常量池
 * 创建时间：2020/3/21 01:29
 * 创 建 人：Lance.WU
 */
public final class SecurityConstants {

    /**
     * 默认的处理验证码的URL前缀
     */
    public static final String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/api/1.0/code/";
    /**
     * 请求需要认证时，默认转换的URL
     *
     * @See SecurityApi
     */
    public static final String DEFAULT_UN_AUTHENTICATION_URL = "/authentication/require";
    /**
     * 默认的用户密码处理请求URL
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";
    /**
     * 默认的手机号处理请求URL
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";
    /**
     * 默认的手机号处理请求URL
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_OPENID = "/authentication/openId";
    /**
     * 默认的登录页面
     */
    public static final String DEFAULT_LOGIN_IN_URL = "/default-signIn.html";
    /**
     * 默认的注册页面
     */
    public static final String DEFAULT_LOGIN_UP_URL = "/default-signUp.html";
    /**
     * 存储验证码存储Session的前缀
     */
    public static final String DEFAULT_VALIDATE_SESSION_PREFIX = "DEFAULT_VALIDATE_SESSION_PREFIX_";
    /**
     * 校验码生成逻辑的默认生成器后缀
     */
    public static final String VALIDATE_CODE_GENERATOR_SUFFIX = "CodeGenerator";
    /**
     *
     */
    public static final String VALIDATE_CODE_PROCESSOR_SUFFIX = "CodeProcessor";
    /**
     * 默认校验码发送短信字段段默认名称
     */
    public static final String DEFAULT_MOBILE_INPUT_NAME_KEY = "mobile";
    /**
     * 默认短信校验码发送服务端验证字段的默认名称
     */
    public static final String DEFAULT_CODE_INPUT_NAME_SMS_KEY = "smsCode";
    /**
     * 默认图型校验码发送服务端验证字段的默认名称
     */
    public static final String DEFAULT_CODE_INPUT_NAME_IMAGE_KEY = "imageCode";

    /**
     * Social的参数名称-openId
     */
    public static final String DEFAULT_SOCIAL_PARAMETER_NAME_OPEN_ID = "openId";
    /**
     * Social的参数名称-providerId
     */
    public static final String DEFAULT_SOCIAL_PARAMETER_NAME_PROVIDER_ID = "providerId";

    /**
     * Session失败跳转页面
     */
    public static final String DEFAULT_SESSION_INVALID_URL = "/default-session-invalid.html";


}
