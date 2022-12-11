package com.cy.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.auth.pojo.entity.AclModule;
import com.cy.auth.pojo.param.aclmodule.AclModuleDelParam;
import com.cy.auth.pojo.param.aclmodule.AclModuleParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lil-K
 * @since 2020-11-26
 */
public interface AclModuleService extends IService<AclModule> {

    ApiResp addAclModule(AclModuleParam param) throws Exception;

    ApiResp editAclModule(AclModuleParam param) throws Exception;

    ApiResp aclModuleTree() throws Exception;

    ApiResp delete(AclModuleDelParam param) throws Exception;
}
