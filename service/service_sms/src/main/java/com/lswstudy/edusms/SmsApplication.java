package com.lswstudy.edusms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lswstudy
 * @create 2022-02-24-16:56
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.lswstudy"})
@EnableDiscoveryClient  //nacos
public class SmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class,args);
    }
}
