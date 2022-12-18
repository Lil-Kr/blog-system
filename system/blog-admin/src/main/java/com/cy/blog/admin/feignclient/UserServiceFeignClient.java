package com.cy.blog.admin.feignclient;

import com.cy.common.model.userserver.api.UserServerApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

/**
 * @Author: Lil-K
 * @Date: 2022/12/11
 * @Description:
 */
@Service
@FeignClient(name = "user-server-01", path = "user")
public interface UserServiceFeignClient extends UserServerApi {

}
