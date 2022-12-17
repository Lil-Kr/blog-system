package com.cy.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Lil-K
 * @Date: 2022/12/17
 * @Description: gateway filter
 */
@Component
@Data
@ConfigurationProperties("gateway-config.url")
public class SkipAuthUrlProperties {

    private List<String> skipUrls;
}
