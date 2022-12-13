package com.cy.blog.admin.service.Impl;

import com.cy.blog.admin.feignclient.UserServiceFeignClient;
import com.cy.blog.admin.pojo.param.AdminLoginParam;
import com.cy.blog.admin.service.LoginService;
import com.cy.common.utils.apiUtil.ApiResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Lil-K
 * @Date: 2022/12/11
 * @Description:后台admin登录 service impl
 */
@Service("login-service-impl")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserServiceFeignClient userServiceFeignClient;

    @Override
    public ApiResp adminLogin(AdminLoginParam param) throws Exception {
        // 调用user-server做逻辑判断
        return userServiceFeignClient.userInfo(param.getLoginAccount(), param.getPassword());
    }

    @Override
    public ApiResp loginOut() throws Exception {
        return ApiResp.success("登出成功");
    }
}
