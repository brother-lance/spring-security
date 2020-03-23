package org.dream.base.core.social;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：security
 * 类 名 称：DefaultConnectStatusView
 * 类 描 述：页面显示视图
 * 创建时间：2020/3/23 19:34
 * 创 建 人：Lance.WU
 */
@Component("connect/status")
public class DefaultConnectStatusView extends AbstractView {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, List<Connection<?>>> map = (Map<String, List<Connection<?>>>) model.get("connectionMap");

        Map<String, Boolean> resultMap = new HashMap<>();
        map.keySet().forEach(e -> {
            resultMap.put(e, !CollectionUtils.isEmpty(map.get(e)));
        });

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(resultMap));

    }
}
