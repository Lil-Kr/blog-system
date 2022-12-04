package com.cy.sys.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class CacheManager {

    /**
     * 初始化内容
     * @throws Exception
     */
    @Scheduled(cron = "0 */120 * * * ?")
    @PostConstruct
    public void initData() throws Exception {
    }
}
