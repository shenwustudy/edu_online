package com.lswstudy.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lswstudy
 * @create 2021-12-17-13:45
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.lswstudy"})
@EnableDiscoveryClient
public class OssApplication {
    public static void main(String[] args) { SpringApplication.run(OssApplication.class,args); }
}
