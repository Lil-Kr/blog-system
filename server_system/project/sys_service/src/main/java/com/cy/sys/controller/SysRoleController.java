package com.cy.sys.controller;


import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.dto.aclmodule.AclModuleDto;
import com.cy.sys.pojo.param.role.RoleListPageParam;
import com.cy.sys.pojo.param.role.RoleSaveParam;
import com.cy.sys.pojo.param.roleacl.RoleAclSaveParam;
import com.cy.sys.pojo.param.roleuser.RoleUserParam;
import com.cy.sys.service.ISysRoleAclService;
import com.cy.sys.service.ISysRoleService;
import com.cy.sys.service.ISysRoleUserService;
import com.cy.sys.service.impl.SysTreeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 角色管理模块
 * @author CY
 * @since 2020-11-28
 */
@RestController
@RequestMapping("/sys/role")
@Slf4j
public class SysRoleController {

    @Resource
    private ISysRoleService sysRoleService1;

    @Resource
    private SysTreeService sysTreeService1;

    @Resource
    private ISysRoleUserService sysRoleUserService1;

    @Resource
    private ISysRoleAclService sysRoleAclService1;

    /**
     * 分页查询角色列表
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("listPage")
    public ApiResp listPage (@RequestBody @Valid RoleListPageParam param) throws Exception {
        return sysRoleService1.listPage(param);
    }

    /**
     * 保存角色信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("add")
    public ApiResp add (@RequestBody @Valid RoleSaveParam param) throws Exception {
        return sysRoleService1.add(param);
    }

    /**
     * 修改角色信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("edit")
    public ApiResp edit (@RequestBody @Valid RoleSaveParam param) throws Exception {
        return sysRoleService1.edit(param);
    }

    /**
     * 删除角色信息
     * @param param
     */
    @PostMapping("delete")
    public ApiResp delete (@RequestBody @Validated({RoleSaveParam.GroupTreeOrDel.class}) RoleSaveParam param) throws Exception {
        return sysRoleService1.delete(param);
    }

    /**
     * 获取当前用户所拥有的[角色-权限]树
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("roleTree")
    public ApiResp roleTree(@RequestBody @Validated({RoleSaveParam.GroupTreeOrDel.class}) RoleSaveParam param) throws Exception {
        List<AclModuleDto> aclModuleDtoList = sysTreeService1.roleTree(param.getSurrogateId());
        if (CollectionUtils.isNotEmpty(aclModuleDtoList)) {
            return ApiResp.success(aclModuleDtoList);
        }else {
            return ApiResp.failure("该角色下没有权限点明细");
        }
    }

    /**
     * 获取所有角色信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("listAll")
    public ApiResp listAll (@RequestBody @Valid RoleListPageParam param) throws Exception {
        return sysRoleService1.listAll(param);
    }

    /**
     * 修改角色对应的权限点
     * 维护[角色-权限]关系接口
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("changeRoleAcls")
    public ApiResp changeRoleAcls(@RequestBody @Validated({RoleAclSaveParam.GroupChangeAcls.class}) RoleAclSaveParam param) throws Exception {
        return sysRoleAclService1.changeRoleAcls(param);
    }

    /**
     * 获取[角色-用户]列表
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

}

