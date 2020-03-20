package org.dream.base.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 项目名称：security
 * 类 名 称：TimeHandler
 * 类 描 述：时间拦截器
 * 创建时间：2020/3/18 12:00
 * 创 建 人：Lance.WU
 */
@Component
@Slf4j
public class TimeHandler implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        log.info("TimeHandler-preHandle 调用..");
//        log.info("TimeHandler-preHandle 调用方法：{}", ((HandlerMethod) handler).getMethod().getName());
//        log.info("TimeHandler-preHandle 调用对像：{}", ((HandlerMethod) handler).getBean().getClass().getName());
        request.setAttribute("startTime", new Date().getTime());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        log.info("TimeHandler-postHandle 执行拦截器");
//        log.info("TimeHandler-postHandle 视图信息：{},", modelAndView);
        long startTime = (long) request.getAttribute("startTime");
//        log.info("TimeHandler-postHandle调用方法耗时：" + (System.currentTimeMillis() - startTime));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        log.info("TimeHandler-afterCompletion 执行拦截器");
//        log.info("TimeHandler-afterCompletion 错误信息：{}", ex);
        long startTime = (long) request.getAttribute("startTime");
//        log.info("TimeHandler-afterCompletion 调用方法耗时：" + (System.currentTimeMillis() - startTime));
    }
}
