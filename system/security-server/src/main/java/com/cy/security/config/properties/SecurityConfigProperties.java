package com.cy.security.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Lil-K
 * @Date: 2022/12/20
 * @Description: 相关配置
 */
@Component
@Data
@ConfigurationProperties("security-config")
public class SecurityConfigProperties {

    private List<String> filterRequestUrls;

}
