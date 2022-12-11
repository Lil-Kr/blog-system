package com.cy.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.auth.pojo.entity.User;
import com.cy.auth.pojo.param.user.UserDelParam;
import com.cy.auth.pojo.param.user.UserListPageParam;
import com.cy.auth.pojo.param.user.UserSaveParam;
import com.cy.auth.pojo.param.user.UserUpdatePwdParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lil-K
 * @since 2020-11-26
 */
public interface UserService extends IService<User> {

    ApiResp edit(UserSaveParam param) throws Exception;

    ApiResp add(UserSaveParam param) throws Exception;

    User findByLoginAccount(String LoginAccount) throws Exception;

    ApiResp delete(UserDelParam param) throws Exception;

    ApiResp listPage(UserListPageParam param) throws Exception;

    ApiResp listAll() throws Exception;

    ApiResp updatePassword(UserUpdatePwdParam param) throws Exception;


}
