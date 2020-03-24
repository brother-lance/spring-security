package org.dream.base.brower.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.dream.base.brower.support.SimpleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 项目名称：security
 * 类 名 称：AbstractSessionStrategy
 * 类 描 述：
 * 创建时间：2020/3/24 22:20
 * 创 建 人：Lance.WU
 */
@Slf4j
public class AbstractSessionStrategy {

    /**
     * 跳转的url
     */
    private String destinationUrl;
    /**
     * 重定向策略
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    /**
     * 跳转前是否创建新的session
     */
    private boolean createNewSession = true;

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @param invalidSessionUrl
     */
    public AbstractSessionStrategy(String invalidSessionUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
        this.destinationUrl = invalidSessionUrl;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.web.session.InvalidSessionStrategy#
     * onInvalidSessionDetected(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    protected void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (createNewSession) {
            request.getSession();
        }

        String sourceUrl = request.getRequestURI();
        String targetUrl;

        if (StringUtils.endsWithIgnoreCase(sourceUrl, ".html")) {
            targetUrl = destinationUrl + ".html";
            log.info("session失效,跳转到" + targetUrl);
            redirectStrategy.sendRedirect(request, response, targetUrl);
        } else {
            String message = "session已失效";
            if (isConcurrency()) {
                message = message + "，有可能是并发登录导致的";
            }
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(message)));
        }

    }

    /**
     * session失效是否是并发导致的
     *
     * @return
     */
    protected boolean isConcurrency() {
        return false;
    }
}
