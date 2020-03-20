package org.dream.base.mock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * 项目名称：security
 * 类 名 称：WireMock
 * 类 描 述：测试
 * 创建时间：2020/3/18 23:08
 * 创 建 人：Lance.WU
 */
public class WireMockTest {

    public static void main(String[] args) throws IOException {
        WireMock.configureFor(8061);//  配置Mock服务器
        WireMock.removeAllMappings();  // 清空所有信息

        mock("/api/1.0/user/1", "01");
        mock("/api/1.0/user/2", "02");
    }

    public static void mock(String url, String fileName) throws IOException {

        ClassPathResource resource = new ClassPathResource("mock/response/" + fileName + ".txt");

        String content = StringUtils.join(FileUtils.readLines(resource.getFile()
                , "UTF-8").toArray(), "\n");
        WireMock.stubFor(
                WireMock.get(
                        WireMock.urlEqualTo(url)
                ).willReturn(WireMock.aResponse().withBody(content).withStatus(200))
        );
    }
}
