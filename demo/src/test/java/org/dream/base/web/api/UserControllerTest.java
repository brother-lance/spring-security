package org.dream.base.web.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


/**
 * 项目名称：security
 * 类 名 称：UserControllerTest
 * 类 描 述：用户测试
 * 创建时间：2020/3/17 23:21
 * 创 建 人：Lance.WU
 */

public class UserControllerTest extends SpringBaseTest {



//    // 当查询成功的时候执行
//    @Test
//    public void whenQuerySuccess() throws Exception {
//        // 模拟发送一个是http get请求 文本格式是application_json_utf8的数据, 返回的状态码是200
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/1.0/user")
//                .contentType(MediaType.APPLICATION_JSON_UTF8))  /** 发送http get 请求 */
//                .andExpect(MockMvcResultMatchers.status().isOk())  /** 状态码200 */
//                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3)); /** 判断长度是3 */
//
//    }

//    // 当查询成功的时候执行
//    @Test
//    public void whenQuerySuccess() throws Exception {
//        // 模拟发送一个是http get请求 文本格式是application_json_utf8的数据, 返回的状态码是200
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/1.0/user")
//                .param("userName", "jo") /** 添加参数 */
//                .contentType(MediaType.APPLICATION_JSON_UTF8))  /** 发送http get 请求 */
//                .andExpect(MockMvcResultMatchers.status().isOk())  /** 状态码200 */
//                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3)); /** 判断长度是3 */
//
//    }

//    // 当查询成功的时候执行  传送后台对像接收
//    @Test
//    public void whenQuerySuccess() throws Exception {
//        // 模拟发送一个是http get请求 文本格式是application_json_utf8的数据, 返回的状态码是200
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/1.0/user")
//                .param("userName", "jo") /** 添加参数 */
//                .param("age", "18") /** 添加参数 */
//                .param("ageTo", "60") /** 添加参数 */
//                .contentType(MediaType.APPLICATION_JSON_UTF8))  /** 发送http get 请求 */
//                .andExpect(MockMvcResultMatchers.status().isOk())  /** 状态码200 */
//                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3)); /** 判断长度是3 */
//
//    }

    // 当查询成功的时候执行  传送后台对像-分页
    @Test
    public void whenQuerySuccess() throws Exception {
        // 模拟发送一个是http get请求 文本格式是application_json_utf8的数据, 返回的状态码是200
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/api/1.0/user")
                .param("userName", "jo") /** 添加参数 */
                .param("age", "18") /** 添加参数 */
                .param("ageTo", "60") /** 添加参数 */
//                .param("size", "60") /** 分页参数 */
//                .param("page", "3") /** 分页参数 */
//                .param("sort", "age, desc") /** 排序参数 */
                .contentType(MediaType.APPLICATION_JSON_UTF8))  /** 发送http get 请求 */
                .andExpect(MockMvcResultMatchers.status().isOk())  /** 状态码200 */
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))/** 判断长度是3 */
                .andReturn().getResponse().getContentAsString();

        System.out.println("result:" + result);

    }


    /**
     * 用户详情测试 用户一个是用户是1的用户详情
     */
    @Test
    public void whenGenInfoSuccess() throws Exception {

        String result = mockMvc.perform(MockMvcRequestBuilders.get("/api/1.0/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("tom"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("result:" + result);

    }

    /**
     * 查询返回错误信息
     *
     * @throws Exception
     */
    @Test
    public void whenGenInfoFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/1.0/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    /**
     * 查询返回错误信息
     *
     * @throws Exception
     */
    @Test
    public void whenCreateSuccess() throws Exception {

        Date d = new Date(); // 时间一般使用时间戳传送

//        String content = "{\"userName\":\"张三\",\"password\":\"1123456\",\"birthday\":" + d.getTime() + "}";
        String content = "{\"userName\":\"张三\",\"password\":null,\"birthday\":" + d.getTime() + "}";
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/api/1.0/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    /**
     * 修改用户信息
     *
     * @throws Exception
     */
    @Test
    public void whenUpdateSuccess() throws Exception {
        // 加一年的时间
        Date d = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

        String content = "{\"id\":\"1\", \"userName\":\"张三\",\"password\":\"123456\",\"birthday\":" + d.getTime() + "}";
        String result = mockMvc.perform(MockMvcRequestBuilders.put("/api/1.0/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    /**
     * 删除用户信息
     * @throws Exception
     */
    @Test
    public void whenDeleteSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/1.0/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
