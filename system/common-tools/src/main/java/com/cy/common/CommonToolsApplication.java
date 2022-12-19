package com.cy.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class CommonToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonToolsApplication.class, args);
    }
}
