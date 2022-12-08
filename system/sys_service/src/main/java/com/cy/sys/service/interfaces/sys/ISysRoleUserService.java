package com.cy.sys.service.interfaces.sys;

import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.sys.entity.SysRoleUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.sys.pojo.sys.param.roleuser.RoleUserParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lil-K
 * @since 2020-11-26
 */
public interface ISysRoleUserService extends IService<SysRoleUser> {

//    ApiResp add(RoleUserParam param) throws Exception;
//
//    ApiResp edit(RoleUserParam param) throws Exception;

    ApiResp changeRoleUsers(RoleUserParam param) throws Exception;

//    ApiResp roleUserPageList(RoleUserParam param) throws Exception;

    ApiResp roleUserList(RoleUserParam param) throws Exception;
}
