package com.cy.security.restapi;

import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.common.model.userserver.pojo.param.AdminLoginParam;
import com.cy.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: Lil-K
 * @Date: 2022/12/15
 * @Description:
 */
@RestController
@RequestMapping("admin")
public class AdminLoginController {

    @Autowired
    @Qualifier("login-service-impl")
    private LoginService loginService;

    @Autowired
    @Qualifier("user-details-service-impl")
    private UserDetailsService userDetailsService;

    @GetMapping("test1")
    public UserDetails test1(@Valid AdminLoginParam param) throws Exception {
//        return loginService.adminLogin(param);
        return userDetailsService.loadUserByUsername(param.getLoginAccount());
    }

    @GetMapping("test2")
    public String test2() throws Exception {
        return "auth-admin-test2";
    }

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
