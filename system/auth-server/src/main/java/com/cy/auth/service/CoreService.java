package com.cy.auth.service;

import com.cy.auth.pojo.entity.Acl;

import java.util.List;

/**
 * @author Lil-K
 * @since 2020-11-26
 */
public interface CoreService {

    /**
     * 获取当前用户所拥有的权限列表
     */
    List<Acl> getCurrentUserAclList() throws Exception;

    /**
     * 获取[角色-权限]列表
     */
    List<Acl> getRoleAclList(Long roleSurrogateId) throws Exception;


    List<Acl> getUserAclList(Long userId) throws Exception;

}
