package com.cy.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.entity.SysRole;
import com.cy.sys.pojo.param.role.RoleListPageParam;
import com.cy.sys.pojo.param.role.RoleSaveParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CY
 * @since 2020-11-26
 */
public interface ISysRoleService extends IService<SysRole> {

    ApiResp add(RoleSaveParam param) throws Exception;

    ApiResp edit(RoleSaveParam param) throws Exception;

    ApiResp listPage(RoleListPageParam param) throws Exception;

    ApiResp delete(RoleSaveParam param) throws Exception;

//    ApiResp delete(Long surrogateId) throws Exception;

    ApiResp listAll(RoleListPageParam param) throws Exception;

    List<SysRole> getRoleListByUserId(long userId) throws Exception;
}
