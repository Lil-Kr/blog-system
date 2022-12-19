package com.cy.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author: Lil-K
 * @Date: 2022/12/19
 * @Description:
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SecurityServerApplication {
    public static void main(String[] args) {

        SpringApplication.run(SecurityServerApplication.class, args);
    }
}
