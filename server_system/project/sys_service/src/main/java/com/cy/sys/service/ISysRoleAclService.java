package com.cy.sys.service;

import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.entity.SysRoleAcl;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.sys.pojo.param.roleacl.RoleAclSaveParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CY
 * @since 2020-11-26
 */
public interface ISysRoleAclService extends IService<SysRoleAcl> {

    ApiResp changeRoleAcls(RoleAclSaveParam param) throws Exception;

}
