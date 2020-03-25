package org.dream.base.web.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 项目名称：security
 * 类 名 称：FileUploadControllerTest
 * 类 描 述：
 * 创建时间：2020/3/18 17:37
 * 创 建 人：Lance.WU
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FileUploadControllerTest extends  SpringBaseTest {

    /**
     * 上传文件
     */
    @Test
    public void whenUploadFileSuccess() throws Exception {
        // 模拟文件上传
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/api/1.0/file")
                .file(new MockMultipartFile("file", "test.txt",
                        MediaType.MULTIPART_FORM_DATA.getType(), "Hello world".getBytes("utf-8")))
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }
}
