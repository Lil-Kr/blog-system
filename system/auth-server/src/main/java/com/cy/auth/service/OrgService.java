package com.cy.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.auth.pojo.entity.Org;
import com.cy.auth.pojo.param.org.OrgDeleteParam;
import com.cy.auth.pojo.param.org.OrgGetChildrenParam;
import com.cy.auth.pojo.param.org.OrgListAllParam;
import com.cy.auth.pojo.param.org.OrgParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lil-K
 * @since 2020-11-24
 */
public interface OrgService extends IService<Org> {

    ApiResp add(OrgParam param) throws Exception;

    ApiResp edit(OrgParam param) throws Exception;

    ApiResp getChildrenOrgList(OrgGetChildrenParam dto) throws Exception;

    ApiResp orgTree() throws Exception;

    ApiResp delete(OrgDeleteParam dto) throws Exception;

    ApiResp orgListAll(OrgListAllParam param) throws Exception;

    ApiResp orgListPage(OrgListAllParam param) throws Exception;
}
