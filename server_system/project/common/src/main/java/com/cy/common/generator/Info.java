package com.cy.common.generator;

import lombok.Builder;
import lombok.Data;

/**
 * @creator Screenly
 * @create_time 2020/4/27
 * @description
 **/
@Data
@Builder
public class Info {

    private String driver;
    private String url;
    private String username;
    private String password;
    private String outputPath;
    private String[] tableNames;

}
