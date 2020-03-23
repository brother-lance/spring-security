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
// @Component("connect/weixinview")  // TODO 此处是需要给多个视图使用
public class DefaultConnectView extends AbstractView {


    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        String html = model.get("connect") == null ? "<h3>绑定成功</h3>" : "<h3>解绑成功</h3>";
        response.getWriter().write(html);
    }
}
