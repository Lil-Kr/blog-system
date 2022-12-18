package com.cy.user.restapi;

import com.cy.common.model.userserver.api.UserServerApi;
import com.cy.common.model.userserver.pojo.entity.User;
import com.cy.common.model.userserver.pojo.param.UserDelParam;
import com.cy.common.model.userserver.pojo.param.UserListPageParam;
import com.cy.common.model.userserver.pojo.param.UserSaveParam;
import com.cy.common.model.userserver.pojo.param.UserUpdatePwdParam;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.user.servic.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 用户服务模块
 * @author Lil-K
 * @since 2020-11-26
 */
@RestController
@RequestMapping("user")
public class UserApi implements UserServerApi {

    @Autowired
    private UserService userService;

    @Override
    public ApiResp userInfo(String loginAccount, String password) throws Exception {
        User user = userService.findByLoginAccountAndPwd(loginAccount, password);
        if (Objects.isNull(user)) {
            return ApiResp.error("用户不存在");
        }else {
            return ApiResp.success(user);
        }
    }

    @Override
    public ApiResp userInfo(String loginAccount) throws Exception {
        User user = userService.findByLoginAccount(loginAccount);
        if (Objects.isNull(user)) {
            return ApiResp.error("用户不存在");
        }else {
            return ApiResp.success(user);
        }
    }

    @Override
    public ApiResp listPage(UserListPageParam param) throws Exception {
        return userService.listPage(param);
    }

    @Override
    public ApiResp save(UserSaveParam param) throws Exception {
        if (Objects.nonNull(param.getId()) && Objects.nonNull(param.getSurrogateId())) {// update
            return userService.edit(param);
        }else { // insert
            return userService.add(param);
        }
    }

    @Override
    public ApiResp add(UserSaveParam param) throws Exception {
        return userService.add(param);
    }

    @Override
    public ApiResp edit(UserSaveParam param) throws Exception {
        return userService.edit(param);
    }

    @Override
    public ApiResp delete(UserDelParam param) throws Exception {
        return userService.delete(param);
    }

    @Override
    public ApiResp updatePassword(UserUpdatePwdParam param) throws Exception {
        return userService.updatePassword(param);
    }
}