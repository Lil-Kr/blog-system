package com.cy.blog.admin.service;

import com.cy.blog.admin.pojo.param.AdminLoginParam;
import com.cy.common.utils.apiUtil.ApiResp;

/**
 * @Author: Lil-K
 * @Date: 2022/12/11
 * @Description: 后台admin登录 service
 */
public interface LoginService {

    ApiResp adminLogin(AdminLoginParam param);

}
