package com.cy.user.servic;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.user.pojo.entity.User;
import com.cy.user.pojo.param.UserDelParam;
import com.cy.user.pojo.param.UserListPageParam;
import com.cy.user.pojo.param.UserSaveParam;
import com.cy.user.pojo.param.UserUpdatePwdParam;
import com.cy.common.utils.apiUtil.ApiResp;

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

    User findByLoginAccountAndPwd(String LoginAccount,String password) throws Exception;

    ApiResp delete(UserDelParam param) throws Exception;

    ApiResp listPage(UserListPageParam param) throws Exception;

    ApiResp listAll() throws Exception;

    ApiResp updatePassword(UserUpdatePwdParam param) throws Exception;


}
