package org.dream.base.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * 项目名称：security
 * 类 名 称：TimeFilter
 * 类 描 述：时间记录过滤器-自定义
 * 创建时间：2020/3/18 11:22
 * 创 建 人：Lance.WU
 */
@Slf4j
//@Component
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("TimeFilter 初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //log.info("TimeFilter start");
        long startTime = System.currentTimeMillis();
        chain.doFilter(request, response);
        //log.info("TimeFilter使用时间：" + (System.currentTimeMillis() - startTime));
    }

    @Override
    public void destroy() {
        log.info("TimeFilter 销毁");

    }
}
