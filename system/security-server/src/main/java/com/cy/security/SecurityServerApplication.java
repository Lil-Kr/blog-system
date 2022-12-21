package com.cy.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: Lil-K
 * @Date: 2022/12/19
 * @Description:
 */
@Slf4j
@EnableFeignClients(basePackages = "com.cy.security.feignclient")
@EnableDiscoveryClient
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SecurityServerApplication {
    public static void main(String[] args) {

        SpringApplication.run(SecurityServerApplication.class, args);
    }
}
