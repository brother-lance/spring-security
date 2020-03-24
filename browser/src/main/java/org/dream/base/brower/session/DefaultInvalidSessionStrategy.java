package org.dream.base.brower.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 项目名称：security
 * 类 名 称：SessionInformationExpiredStrategy
 * 类 描 述：
 * 创建时间：2020/3/24 21:49
 * 创 建 人：Lance.WU
 */
@Slf4j
public abstract class DefaultInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {


    public DefaultInvalidSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        onSessionInvalid(request, response);
    }


}
