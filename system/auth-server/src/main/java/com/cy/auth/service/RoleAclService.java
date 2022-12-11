package com.cy.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.auth.pojo.entity.RoleAcl;
import com.cy.auth.pojo.param.roleacl.RoleAclSaveParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lil-K
 * @since 2020-11-26
 */
public interface RoleAclService extends IService<RoleAcl> {

    ApiResp changeRoleAcls(RoleAclSaveParam param) throws Exception;

}
