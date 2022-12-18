package com.cy.security.service;

import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.common.model.userserver.pojo.param.AdminLoginParam;

/**
 * @Author: Lil-K
 * @Date: 2022/12/18
 * @Description:
 */
public interface LoginService {

    ApiResp adminLogin(AdminLoginParam param) throws Exception;

    ApiResp loginOut() throws Exception;
}
