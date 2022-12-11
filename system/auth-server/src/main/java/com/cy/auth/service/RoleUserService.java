package com.cy.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.auth.pojo.entity.RoleUser;
import com.cy.auth.pojo.param.roleuser.RoleUserParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lil-K
 * @since 2020-11-26
 */
public interface RoleUserService extends IService<RoleUser> {

//    ApiResp add(RoleUserParam param) throws Exception;
//
//    ApiResp edit(RoleUserParam param) throws Exception;

    ApiResp changeRoleUsers(RoleUserParam param) throws Exception;

//    ApiResp roleUserPageList(RoleUserParam param) throws Exception;

    ApiResp roleUserList(RoleUserParam param) throws Exception;
}
