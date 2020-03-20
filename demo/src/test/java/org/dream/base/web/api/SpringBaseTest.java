package org.dream.base.web.api;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 项目名称：security
 * 类 名 称：SpringBaseTest
 * 类 描 述：
 * 创建时间：2020/3/19 14:19
 * 创 建 人：Lance.WU
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBaseTest {

    // MVC测试类
    @Autowired
    private WebApplicationContext wac;

    // 伪造的测试环境
    protected MockMvc mockMvc;

    // 执行测试用例之前执行
    @Before
    public void before() {
        // 建造一个是关于mockmvc的环境
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

}
