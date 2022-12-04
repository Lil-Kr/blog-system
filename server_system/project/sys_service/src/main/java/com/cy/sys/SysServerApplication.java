package com.cy.sys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.cy.sys.dao")
@SpringBootApplication
@ServletComponentScan
@EnableAsync // 开启异步注解功能
@EnableScheduling // 开启基于注解的定时任务
public class SysServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysServerApplication.class, args);
    }

}
