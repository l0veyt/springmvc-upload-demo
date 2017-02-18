package com.xinlee.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by xin.lee on 2017/2/18.
 * 文件上传控制器
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    @RequestMapping("/gotoUploadFile")
    public String gotoUploadFile() {
        return "fileupload";
    }

    @RequestMapping("/uploadFile")
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile != null) {
            String originalFilename = multipartFile.getOriginalFilename();                                      // 上传文件的文件名
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));                      // 上传文件的后缀名
            String filepath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator;    // 保存文件的路径
            String filename = UUID.randomUUID().toString().replace("-", "") + suffix;                           // 保存文件的文件名
            multipartFile.transferTo(new File(filepath + filename));                                            // 写入文件
            /**
             * 一般还要把图片的路径存储到数据库中，以便读取展示，此处为文件上传的演示Demo顾不做此功能
             */
        }
        return "success";
    }
}
