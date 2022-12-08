package com.cy.sys.service.interfaces.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.sys.entity.SysAcl;
import com.cy.sys.pojo.sys.param.acl.AclPageParam;
import com.cy.sys.pojo.sys.param.acl.AclParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lil-K
 * @since 2020-11-26
 */
public interface ISysAclService extends IService<SysAcl> {

    ApiResp addAcl(AclParam param) throws Exception;

    ApiResp editAcl(AclParam param) throws Exception;

    ApiResp listPage(AclPageParam param) throws Exception;

    ApiResp acls(AclParam param) throws Exception;
}
