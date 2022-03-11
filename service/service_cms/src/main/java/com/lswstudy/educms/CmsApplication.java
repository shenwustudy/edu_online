package com.lswstudy.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lswstudy
 * @create 2022-02-23-13:15
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lswstudy"}) //指定扫描位置
@MapperScan("com.lswstudy.educms.mapper")
@EnableDiscoveryClient  //nacos
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
