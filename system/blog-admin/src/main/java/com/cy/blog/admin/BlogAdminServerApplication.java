package com.cy.blog.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: Lil-K
 * @Date: 2022/12/11
 * @Description:
 */
@SpringBootApplication
@EnableAsync // 开启异步注解功能
@EnableScheduling // 开启基于注解的定时任务
@EnableFeignClients
//@EnableDiscoveryClient
public class BlogAdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogAdminServerApplication.class, args);
    }
}
