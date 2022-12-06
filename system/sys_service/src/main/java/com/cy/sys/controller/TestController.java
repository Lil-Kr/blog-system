package com.cy.sys.controller;

import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.common.holder.RequestHolder;
import com.cy.sys.pojo.entity.SysUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/test")
public class TestController {

    @PostMapping("test1")
    public ApiResp test1(){
        SysUser user = RequestHolder.getCurrentUser();
        return ApiResp.success(user);
    }

}
