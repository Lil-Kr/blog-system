package com.cy.sys.service;

import com.cy.sys.pojo.entity.SysAcl;

import java.util.List;

/**
 * @author CY
 * @since 2020-11-26
 */
public interface ISysCoreService {

    /**
     * 获取当前用户所拥有的权限列表
     */
    List<SysAcl> getCurrentUserAclList() throws Exception;

    /**
     * 获取[角色-权限]列表
     */
    List<SysAcl> getRoleAclList(Long roleSurrogateId) throws Exception;


    List<SysAcl> getUserAclList(Long userId) throws Exception;

}
