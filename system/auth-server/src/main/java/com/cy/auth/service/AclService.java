package com.cy.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.auth.pojo.entity.Acl;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.auth.pojo.param.acl.AclPageParam;
import com.cy.auth.pojo.param.acl.AclParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lil-K
 * @since 2020-11-26
 */
public interface AclService extends IService<Acl> {

    ApiResp addAcl(AclParam param) throws Exception;

    ApiResp editAcl(AclParam param) throws Exception;

    ApiResp listPage(AclPageParam param) throws Exception;

    ApiResp acls(AclParam param) throws Exception;
}
