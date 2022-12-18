package com.cy.security.service.impl;

import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.common.model.userserver.pojo.entity.User;
import com.cy.security.config.exception.ApiException;
import com.cy.security.feignclient.UserServiceFeignClient;
import com.cy.security.pojo.entity.AdminUserLogin;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author: Lil-K
 * @Date: 2022/12/18
 * @Description:
 */
@Service("user-details-service-impl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserServiceFeignClient userServiceFeignClient;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String loginAccount) throws UsernameNotFoundException {
        /**
         * 根据 loginAccount 查询 用户信息
         */
        ApiResp apiResp = userServiceFeignClient.userInfo(loginAccount);
        User userInfo = (User)apiResp.getData();
        if (apiResp.getCode() != ApiResp.CODE_SUCCESS || Objects.isNull(userInfo)) {
            throw new ApiException("用户名或密码错误");
        }

        /**
         * 来到这里, 说明用户是存在的
         * 将用户信息封装为 UserDetails 类型
         */
        return new AdminUserLogin(userInfo);
    }
}
