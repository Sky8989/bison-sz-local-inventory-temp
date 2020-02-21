package com.bison.inventory;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.util.*;

@MapperScan("com.bison.inventory.mapper.mybatis")
@SpringBootApplication
@EnableEurekaClient
public class BisonSzLocalInventoryTempApplication {






    public static void main(String[] args) {

        SpringApplication.run(BisonSzLocalInventoryTempApplication.class, args);


    }






}
