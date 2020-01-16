package com.bison.inventory;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@MapperScan("com.bison.inventory.mapper.mybatis")
@SpringBootApplication
@EnableEurekaClient
public class BisonSzLocalInventoryTempApplication {

    public static void main(String[] args) {
        SpringApplication.run(BisonSzLocalInventoryTempApplication.class, args);


    }




}
