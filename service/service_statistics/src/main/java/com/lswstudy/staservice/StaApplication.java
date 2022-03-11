package com.lswstudy.staservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author lswstudy
 * @create 2022-03-03-14:36
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lswstudy"})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.lswstudy.staservice.mapper")
@EnableScheduling   //定时任务
public class StaApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaApplication.class,args);
    }
}
