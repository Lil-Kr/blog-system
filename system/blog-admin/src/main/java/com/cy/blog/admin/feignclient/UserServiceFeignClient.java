package com.cy.blog.admin.feignclient;

import com.cy.common.utils.apiUtil.ApiResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: Lil-K
 * @Date: 2022/12/11
 * @Description:
 */
@Service
@FeignClient(name = "user-server", path = "user")
public interface UserServiceFeignClient {

    @GetMapping("userInfo/{loginAccount}/{password}")
    ApiResp userInfo(@PathVariable String loginAccount, @PathVariable String password);

}
