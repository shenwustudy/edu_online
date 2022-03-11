package com.lswstudy.eduservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lswstudy
 * @create 2021-10-08-13:31
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lswstudy"})
@EnableDiscoveryClient  //nacos
@EnableFeignClients //feign
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
