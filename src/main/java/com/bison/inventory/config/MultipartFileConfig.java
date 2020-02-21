package com.bison.inventory.config;

import com.bison.inventory.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.io.FileNotFoundException;

@Component
public class MultipartFileConfig {


    @Value("${tempFilePath}")
    String tempFilePath;

    /**
     * 配置上次文件大小
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() throws FileNotFoundException {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小 10M
        factory.setMaxFileSize("10240KB");
        /// 总上传数据大小 10M
        factory.setMaxRequestSize("10240KB");

        //1:提先建好临时文件目录
        //获取项目根路径 ../../bison-sz-local-inventory-temp
        String rootPath =FileUploadUtil.getRootPath();
        File file = new File( rootPath + "/"+tempFilePath);
        if(!file.exists()){
            boolean flag =  file.mkdirs();
            System.out.println("=== flag  = " + flag);
        }


        factory.setLocation(file.getPath());

        return factory.createMultipartConfig();
    }






}
