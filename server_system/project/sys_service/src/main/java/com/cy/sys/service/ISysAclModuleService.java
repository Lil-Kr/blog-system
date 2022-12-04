package com.cy.sys.service;

import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.entity.SysAclModule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.sys.pojo.param.aclmodule.AclModuleDelParam;
import com.cy.sys.pojo.param.aclmodule.AclModuleParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CY
 * @since 2020-11-26
 */
public interface ISysAclModuleService extends IService<SysAclModule> {

    ApiResp addAclModule(AclModuleParam param) throws Exception;

    ApiResp editAclModule(AclModuleParam param) throws Exception;

    ApiResp aclModuleTree() throws Exception;

    ApiResp delete(AclModuleDelParam param) throws Exception;
}
