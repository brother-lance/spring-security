package org.dream.base.brower.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 项目名称：security
 * 类 名 称：ImoocExpiredSessionStrategy
 * 类 描 述：
 * 创建时间：2020/3/24 22:23
 * 创 建 人：Lance.WU
 */
public class DefaultExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

    public DefaultExpiredSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        onSessionInvalid(event.getRequest(), event.getResponse());
    }


    @Override
    protected boolean isConcurrency() {
        return true;
    }
}
