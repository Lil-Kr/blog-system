package com.cy.blog.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: Lil-K
 * @Date: 2022/12/11
 * @Description:
 */

@Slf4j
//@MapperScan("com.cy.user.dao")
@ServletComponentScan
@EnableAsync // 开启异步注解功能
@EnableScheduling // 开启基于注解的定时任务
@EnableFeignClients(basePackages = "com.cy.blog.admin.feignclient")
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BlogAdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogAdminServerApplication.class, args);
    }
}
