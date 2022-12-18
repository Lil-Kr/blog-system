package com.cy.user.servic;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.common.model.userserver.pojo.entity.User;
import com.cy.common.model.userserver.pojo.param.UserDelParam;
import com.cy.common.model.userserver.pojo.param.UserListPageParam;
import com.cy.common.model.userserver.pojo.param.UserSaveParam;
import com.cy.common.model.userserver.pojo.param.UserUpdatePwdParam;

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
