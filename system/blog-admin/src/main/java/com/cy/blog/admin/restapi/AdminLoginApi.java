package com.cy.blog.admin.restapi;

import com.cy.blog.admin.pojo.param.AdminLoginParam;
import com.cy.blog.admin.service.LoginService;
import com.cy.common.utils.apiUtil.ApiResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: Lil-K
 * @Date: 2022/12/11
 * @Description: 后台admin登录
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminLoginApi {

    @Autowired
    @Qualifier("login-service-impl")
    private LoginService loginService;

    /**
     * 后台admin登录
     * @param param
     * @return
     */
    @PutMapping("/login")
    public ApiResp login(@Valid @RequestBody AdminLoginParam param) throws Exception {
        return loginService.adminLogin(param);
    }

    @GetMapping("loginOut")
    public ApiResp loginOut() throws Exception {
        return loginService.loginOut();
    }

}
