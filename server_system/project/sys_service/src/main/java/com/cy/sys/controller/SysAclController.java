package com.cy.sys.controller;


import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.param.acl.AclPageParam;
import com.cy.sys.pojo.param.acl.AclParam;
import com.cy.sys.pojo.param.roleacl.RoleAclSaveParam;
import com.cy.sys.pojo.param.roleuser.RoleUserParam;
import com.cy.sys.service.ISysAclService;
import com.cy.sys.service.ISysRoleAclService;
import com.cy.sys.service.ISysRoleUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 *  权限管理模块
 * @author CY
 * @since 2020-11-28
 */
@RestController
@RequestMapping("/sys/acl")
@Slf4j
public class SysAclController {

    @Resource
    private ISysAclService sysAclService1;

    @Resource
    private ISysRoleUserService sysRoleUserService1;

    @Resource
    private ISysRoleAclService sysRoleAclService1;

    /**
     * 分页查询权限点列表
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("listPage")
    public ApiResp listPage(@RequestBody @Valid AclPageParam param) throws Exception {
        return sysAclService1.listPage(param);
    }

    /**
     * 权限点信息保存
     * @param param
     * @return
     */
    @PostMapping("addAcl")
    public ApiResp addAcl(@RequestBody @Valid AclParam param) throws Exception {
        return sysAclService1.addAcl(param);
    }

    /**
     * 修改权限点信息
     * @param param
     * @return
     */
    @PostMapping("editAcl")
    public ApiResp editAcl(@RequestBody @Valid AclParam param) throws Exception {
        return sysAclService1.editAcl(param);
    }

    /**
     * 获取权限点分配的用户角色
     */
    @PostMapping("acls")
    public ApiResp acls(@RequestBody @Validated({AclParam.GroupAcls.class}) AclParam param) throws Exception {
        return sysAclService1.acls(param);
    }

    /**
     * 获取角色分配的用户列表
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("roleUserList")
    public ApiResp roleUserList(@RequestBody @Validated({RoleUserParam.GroupRoleUserPageList.class}) RoleUserParam param) throws Exception {
        return sysRoleUserService1.roleUserList(param);
    }

    /**
     * 维护[角色-用户]关系接口
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("changeRoleUsers")
    public ApiResp changeRoleUsers(@RequestBody @Validated({RoleUserParam.GroupChangeRoleUsers.class}) RoleUserParam param) throws Exception {
        return sysRoleUserService1.changeRoleUsers(param);
    }

    /**
     * 修改[角色-权限]关系
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("changeRoleAcls")
    public ApiResp changeRoleAcls(@RequestBody @Validated({RoleAclSaveParam.GroupChangeAcls.class}) RoleAclSaveParam param) throws Exception {
        return sysRoleAclService1.changeRoleAcls(param);
    }

}

