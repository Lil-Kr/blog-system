package com.cy.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.auth.pojo.entity.Role;
import com.cy.auth.pojo.param.role.RoleListPageParam;
import com.cy.auth.pojo.param.role.RoleSaveParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lil-K
 * @since 2020-11-26
 */
public interface RoleService extends IService<Role> {

    ApiResp add(RoleSaveParam param) throws Exception;

    ApiResp edit(RoleSaveParam param) throws Exception;

    ApiResp listPage(RoleListPageParam param) throws Exception;

    ApiResp delete(RoleSaveParam param) throws Exception;

//    ApiResp delete(Long surrogateId) throws Exception;

    ApiResp listAll(RoleListPageParam param) throws Exception;

    List<Role> getRoleListByUserId(long userId) throws Exception;
}
