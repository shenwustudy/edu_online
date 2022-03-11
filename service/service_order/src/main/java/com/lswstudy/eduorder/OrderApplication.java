package com.lswstudy.eduorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lswstudy
 * @create 2022-02-28-14:03
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lswstudy"})
@MapperScan("com.lswstudy.eduorder.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class OrderApplication {
    public static void main(String[] args) { SpringApplication.run(OrderApplication.class,args );}
}
