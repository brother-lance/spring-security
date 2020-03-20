package org.dream.base.web.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.dream.base.dto.FileInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * 项目名称：security
 * 类 名 称：FileAPI
 * 类 描 述：
 * 创建时间：2020/3/18 17:46
 * 创 建 人：Lance.WU
 */
@RestController
@RequestMapping("/api/1.0/file")
@Slf4j
public class FileAPI {

    String folder = "/data/upload/";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        log.info("文件名称getName:{}", file.getName());
        log.info("文件名称getOriginalFilename:{}", file.getOriginalFilename());
        log.info("文件名称getSize:{}", file.getSize());

        /** 写本地 */
        File f = new File(folder, new Date().getTime() + ".txt");
        file.transferTo(f); // 传输文件

        /** 写远程  得到流信息，直接上传到远程服务器 */
//        InputStream inputStream = file.getInputStream();

        return new FileInfo(f.getAbsolutePath());
    }

    @GetMapping("{id:\\w+}")
    public void upload(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        log.info("下载文件");
        try (InputStream inputStream = new FileInputStream(new File(folder, id + ".txt")); // 找到本地文件
             OutputStream outputStream = response.getOutputStream(); // 返回客户端文件
        ) {
            response.setContentType("application/x-download");  // 设置下载类型
            response.addHeader("Content-Disposition", "attachment;filename=" + (new Date().getTime()) + ".txt"); //设置文件名称

            IOUtils.copy(inputStream, outputStream); // 将输入流复制到输出流
        } finally {
            log.info("下载文件结束");
        }
    }


//    public String uploadFile(byte[] byteFile, String ext_file, String file_Name) {
//
//        // 拼接服务区的文件路径
//        StringBuffer sbPath = new StringBuffer();
//        sbPath.append("http://192.168.2.200/uploads/");
//        try {
//            // 初始化文件资源
//            ClientGlobal.initByProperties("application.properties");
//
//            // 链接FastDFS服务器，创建tracker和Stroage
//            TrackerClient trackerClient = new TrackerClient();
//            TrackerServer trackerServer = trackerClient.getConnection();
//
//            String storageServerIp = getStorageServerIp(trackerClient, trackerServer);
//            StorageServer storageServer = getStorageServer(storageServerIp);
//            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
//
//            //利用字节流上传文件
////            NameValuePair[] nvps = new NameValuePair[1];
////            nvps[0] = new NameValuePair(file_Name, ext_file);
//            String[] strings = storageClient.upload_file(byteFile, ext_file, null);
//
//            sbPath.append(StrUtil.join("/", strings));
//            log.debug("上传路径=" + sbPath.toString());
//        } catch (IOException | MyException e) {
//            e.printStackTrace();
//        }
//        return sbPath.toString();
//    }
}
