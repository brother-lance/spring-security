package org.dream.base.web.async;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称：security
 * 类 名 称：DeferredResultHandle
 * 类 描 述：
 * 创建时间：2020/3/18 19:39
 * 创 建 人：Lance.WU
 */
@Component
@Getter
@Setter
public class DeferredResultHolder {

    private Map<String, DeferredResult<String>> result = new HashMap<>();

}
