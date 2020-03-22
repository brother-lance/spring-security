package org.dream.base.core.authentication.mobile;

/**
 * 项目名称：security
 * 类 名 称：SmsCodeFilter
 * 类 描 述：
 * 创建时间：2020/3/21 00:03
 * 创 建 人：Lance.WU
 */
@Deprecated
public class SmsCodeFilter  {
//public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean {

//    private static final String SESSION_KEY = "";
//
//    // 失败验证
//    AuthenticationFailureHandler authenticationFailureHandler;
//
//    // Session策略
//    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
//
//    // 安全配置
//    SecurityProperties securityProperties;
//
//    /**
//     * 配置路径安工类
//     */
//    private AntPathMatcher antPathMatcher = new AntPathMatcher();
//
//    // 需要拦截的URL
//    Set<String> urls = new HashSet<>();
//
//    @Override
//    public void afterPropertiesSet() throws ServletException {
//        super.afterPropertiesSet();
//        String[] urlsStr = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getSms().getUrl(), ",");
//        Arrays.asList(urlsStr).forEach(urls::add);
//        urls.add("/authentication/mobile");
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        AtomicBoolean action = new AtomicBoolean(false);
//        urls.forEach(e -> {
//            if (antPathMatcher.matchStart(e, request.getRequestURI()))
//                action.set(true);
//
//        });
//
//        /** 判断当前是否是登录接口，并且是否是POST请求 */
//        if (action.get()) {
//
//            try {
//                validate(new ServletWebRequest(request)); // 验证校验码
//
//            } catch (ValidateCodeException e) {
//                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
//                return;
//            }
//        }
//        // 进行下一步处理
//        filterChain.doFilter(request, response);
//    }
}
