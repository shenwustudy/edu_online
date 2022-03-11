package com.lswstudy.aclservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lswstudy
 * @create 2022-03-05-14:58
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.lswstudy")
@MapperScan("com.lswstudy.aclservice.mapper")
public class AclApplication {
    public static void main(String[] args) {
        SpringApplication.run(AclApplication.class,args);
    }
}
