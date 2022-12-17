package com.cy.blog.admin.service;

import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.downstream.model.userserver.pojo.param.AdminLoginParam;

/**
 * @Author: Lil-K
 * @Date: 2022/12/11
 * @Description: 后台admin登录 service
 */
public interface LoginService {

    ApiResp adminLogin(AdminLoginParam param) throws Exception;

    ApiResp loginOut() throws Exception;

}
