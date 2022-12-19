package com.cy.common.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author: Lil-K
 * @Date: 2022/12/17
 * @Description:
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class CommonModelApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonModelApplication.class, args);
    }
}
