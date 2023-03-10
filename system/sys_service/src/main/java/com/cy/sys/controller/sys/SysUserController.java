package com.cy.sys.controller.sys;

import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.sys.dto.aclmodule.AclModuleDto;
import com.cy.sys.pojo.sys.entity.SysRole;
import com.cy.sys.pojo.sys.param.user.UserDelParam;
import com.cy.sys.pojo.sys.param.user.UserListPageParam;
import com.cy.sys.pojo.sys.param.user.UserSaveParam;
import com.cy.sys.pojo.sys.param.user.UserUpdatePwdParam;
import com.cy.sys.service.interfaces.sys.ISysCoreService;
import com.cy.sys.service.interfaces.sys.ISysRoleService;
import com.cy.sys.service.interfaces.sys.ISysUserService;
import com.cy.sys.service.impl.sys.SysTreeService;
import com.google.common.collect.Maps;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 用户管理模块
 * @author Lil-K
 * @since 2020-11-26
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Resource
    private ISysUserService sysUserService1;

    @Resource
    private ISysCoreService sysCoreService1;

    @Resource
    private SysTreeService sysTreeService1;

    @Resource
    private ISysRoleService sysRoleService;

    /**
     * 分页查询列表
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("listPage")
    public ApiResp listPage(@RequestBody @Valid UserListPageParam param) throws Exception {
        return sysUserService1.listPage(param);
    }

    /**
     * 保存用户信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("save")
    public ApiResp save(@RequestBody @Valid UserSaveParam param) throws Exception {

        if (Objects.nonNull(param.getId()) && Objects.nonNull(param.getSurrogateId())) {// update
            return sysUserService1.edit(param);
        }else { // insert
            return sysUserService1.add(param);
        }
    }

    /**
     * 添加用户信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("add")
    public ApiResp add(@RequestBody @Valid UserSaveParam param) throws Exception {
        return sysUserService1.add(param);
    }

    /**
     * 编辑用户信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("edit")
    public ApiResp edit(@RequestBody @Valid UserSaveParam param) throws Exception {
        return sysUserService1.edit(param);
    }

    /**
     * 删除用户信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("delete")
    public ApiResp delete(@RequestBody @Valid UserDelParam param) throws Exception {
        return sysUserService1.delete(param);
    }

    /**
     * 修改用户密码
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("updatePassword")
    public ApiResp updatePassword(@RequestBody @Valid UserUpdatePwdParam param) throws Exception {
        return sysUserService1.updatePassword(param);
    }

    /**
     * 获取用户权限点信息
     * @return
     * @throws Exception
     */
    @PostMapping("getUserAcls")
    public ApiResp getUserAcls(@RequestBody @Validated({UserSaveParam.GroupGetUserAcl.class}) UserSaveParam param) throws Exception {
        List<AclModuleDto> aclModuleDtoList = sysTreeService1.userAclTree(param.getSurrogateId());
        List<SysRole> roleListByUserId = sysRoleService.getRoleListByUserId(param.getSurrogateId());

        Map<String, Object> map = Maps.newHashMap();
        map.put("acls",aclModuleDtoList);
        map.put("roles",roleListByUserId);
        return ApiResp.success(map);
    }
}