package com.cy.auth.restapi;


import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.auth.pojo.param.aclmodule.AclModuleDelParam;
import com.cy.auth.pojo.param.aclmodule.AclModuleParam;
import com.cy.auth.service.AclModuleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;

/**
 * 权限模块管理
 * @author Lil-K
 * @since 2020-11-28
 */
@RestController
@RequestMapping("/aclModule")
public class AclModuleController {

    @Resource
    private AclModuleService sysAclModuleService1;

    /**
     * 保存权限模块信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("save")
    public ApiResp save(@RequestBody @Valid AclModuleParam param) throws Exception {
        if (Objects.isNull(param.getSurrogateId())) { // insert
            return sysAclModuleService1.addAclModule(param);
        }else { // update
            return sysAclModuleService1.editAclModule(param);
        }
    }

    /**
     * 新增权限模块
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("addAclModule")
    public ApiResp addAclModule(@RequestBody @Valid AclModuleParam param) throws Exception {
        return sysAclModuleService1.addAclModule(param);
    }

    /**
     * 更新权限模块
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("editAclModule")
    public ApiResp editAclModule(@RequestBody @Valid AclModuleParam param) throws Exception {
        return sysAclModuleService1.editAclModule(param);
    }

    /**
     * 获得权限模块树
     * @throws Exception
     */
    @PostMapping("aclModuleTree")
    public ApiResp aclModuleTree() throws Exception {
        return sysAclModuleService1.aclModuleTree();
    }

    /**
     * 删除权限模块功能
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("delete")
    public ApiResp delete(@RequestBody @Valid AclModuleDelParam param) throws Exception {
        return sysAclModuleService1.delete(param);
    }

}

