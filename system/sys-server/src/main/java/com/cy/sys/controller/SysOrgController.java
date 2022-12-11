package com.cy.sys.controller;


import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.param.org.OrgDeleteParam;
import com.cy.sys.pojo.param.org.OrgGetChildrenParam;
import com.cy.sys.pojo.param.org.OrgListAllParam;
import com.cy.sys.pojo.param.org.OrgParam;
import com.cy.sys.service.ISysOrgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;

/**
 * 组织架构模块
 * @author CY
 * @since 2020-11-24
 */
@RestController
@RequestMapping("/sys/org")
@Slf4j
public class SysOrgController {

    @Resource
    private ISysOrgService sysOrgService1;

    /**
     * 保存组织
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("save")
    public ApiResp save(@RequestBody @Valid OrgParam param) throws Exception {

        if (Objects.nonNull(param.getId()) && Objects.nonNull(param.getSurrogateId())) {// update
            return sysOrgService1.edit(param);
        }else { // insert
            return sysOrgService1.add(param);
        }
    }

    /**
     * 新增组织信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("add")
    public ApiResp add(@RequestBody @Valid OrgParam param) throws Exception {
        return sysOrgService1.add(param);
    }

    /**
     * 修改组织信息
     * 支持修改组织层级
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("edit")
    public ApiResp edit(@RequestBody @Validated({OrgParam.GroupEdit.class}) OrgParam param) throws Exception {
        return sysOrgService1.edit(param);
    }

    /**
     * 获取组织结构树结构
     * @return
     */
    @PostMapping("orgTreeList")
    public ApiResp orgTreeList() throws Exception {
        return sysOrgService1.orgTree();
    }

    /**
     * 获取组织结构树结构
     * @return
     */
    @PostMapping("orgListPage")
    public ApiResp orgListPage(@RequestBody @Validated({OrgListAllParam.GroupPage.class}) OrgListAllParam param) throws Exception {
        return sysOrgService1.orgListPage(param);
    }

    /**
     * 获取所有组织信息
     * @return
     * @throws Exception
     */
    @PostMapping("orgListAll")
    public ApiResp orgListAll(@RequestBody @Valid OrgListAllParam param) throws Exception {
        return sysOrgService1.orgListAll(param);
    }

    /**
     * 获取当前组织的子组织信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("getChildrenOrgList")
    public ApiResp getChildrenOrgList(@RequestBody @Valid OrgGetChildrenParam param) throws Exception {
        return sysOrgService1.getChildrenOrgList(param);
    }

    /**
     * 删除组织
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("delete")
    public ApiResp delete(@RequestBody @Valid OrgDeleteParam param) throws Exception {
        return sysOrgService1.delete(param);
    }

}

