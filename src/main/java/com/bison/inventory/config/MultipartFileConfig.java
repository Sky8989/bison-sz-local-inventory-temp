package com.bison.inventory.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.MultipartConfigElement;

@Component
public class MultipartFileConfig {

    /**
     * 配置上次文件大小
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小 10M
        factory.setMaxFileSize("10240KB");
        /// 总上传数据大小 10M
        factory.setMaxRequestSize("10240KB");
        return factory.createMultipartConfig();
    }

}
