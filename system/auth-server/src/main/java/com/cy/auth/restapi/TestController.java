package com.cy.auth.restapi;

import com.cy.auth.pojo.entity.User;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.auth.common.holder.RequestHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("test1")
    public ApiResp test1(){
        User user = RequestHolder.getCurrentUser();
        return ApiResp.success(user);
    }

}
