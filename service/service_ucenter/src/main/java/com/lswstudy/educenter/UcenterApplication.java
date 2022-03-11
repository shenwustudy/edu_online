package com.lswstudy.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lswstudy
 * @create 2022-02-25-9:37
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lswstudy"})
@MapperScan("com.lswstudy.educenter.mapper")
@EnableDiscoveryClient
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }
}
