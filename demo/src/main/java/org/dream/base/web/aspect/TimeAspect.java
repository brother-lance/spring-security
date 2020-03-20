package org.dream.base.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 项目名称：security
 * 类 名 称：TimeAspect
 * 类 描 述：
 * 创建时间：2020/3/18 12:24
 * 创 建 人：Lance.WU
 */
@Slf4j
@Aspect
@Component
public class TimeAspect {


    @Around("execution(* org.dream.base.web.api.UserAPI.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
//        log.info("TimeAspect-handleControllerMethod 调用切片信息: start");

        Object[] args = pjp.getArgs();

//        log.info("TimeAspect-handleControllerMethod请求参数信息：{}", args);
        long startTime = System.currentTimeMillis();
        Object proceed = pjp.proceed();

//        log.info("TimeAspect-handleControllerMethod返回参数信息：{}", proceed);
//        log.info("TimeAspect-handleControllerMethod 调用切片信息 end:" + (System.currentTimeMillis() - startTime));
        return proceed;
    }
}
