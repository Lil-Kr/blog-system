package com.cy.sys.service.interfaces.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.sys.entity.SysOrg;
import com.cy.sys.pojo.sys.param.org.OrgDeleteParam;
import com.cy.sys.pojo.sys.param.org.OrgGetChildrenParam;
import com.cy.sys.pojo.sys.param.org.OrgListAllParam;
import com.cy.sys.pojo.sys.param.org.OrgParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lil-K
 * @since 2020-11-24
 */
public interface ISysOrgService extends IService<SysOrg> {

    ApiResp add(OrgParam param) throws Exception;

    ApiResp edit(OrgParam param) throws Exception;

    ApiResp getChildrenorgList(OrgGetChildrenParam dto) throws Exception;

    ApiResp orgTree() throws Exception;

    ApiResp delete(OrgDeleteParam dto) throws Exception;

    ApiResp orgListAll(OrgListAllParam param) throws Exception;

    ApiResp orgListPage(OrgListAllParam param) throws Exception;
}
