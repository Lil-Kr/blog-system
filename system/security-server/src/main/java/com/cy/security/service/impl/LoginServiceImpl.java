package com.cy.security.service.impl;

import com.cy.common.model.userserver.pojo.param.AdminLoginParam;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.common.utils.jwtUtil.JwtTokenUtil;
import com.cy.security.feignclient.UserServiceFeignClient;
import com.cy.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Lil-K
 * @Date: 2022/12/11
 * @Description:后台admin登录 service impl
 */
@Service("login-service-impl")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserServiceFeignClient userServiceFeignClient;

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Override
    public ApiResp adminLogin(AdminLoginParam param) throws Exception {
        /**
         * 1. 使用 AuthenticationManager authenticationToken 进行认证
         * 2. 如果认证未通过, 提示相应的信息
         * 3. 如果认证通过, 使用userid生成一个jwt, jwt存入 ApiResp 响应体中
         */
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(param.getLoginAccount(),param.getPassword());
//        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
//        if (Objects.isNull(authenticate)) {
//            throw new ApiException("登录失败");
//        }

        // 生成jwt token
        Map<String, Object> payload = new HashMap<>();
//        payload.put("user_info",authenticate.getPrincipal());
        String token = JwtTokenUtil.generatorJwtToken(payload);


        Map<String, Object> response = new HashMap<>();
        response.put("access-token",token);
        return ApiResp.success("登陆成功",response);
    }

    @Override
    public ApiResp loginOut() throws Exception {
        return ApiResp.success("登出成功");
    }
}
