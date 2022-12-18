package com.cy.security.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Author: Lil-K
 * @Date: 2022/12/18
 * @Description:
 */
public interface AdminUserLoginService extends UserDetails {

    String getLoginAccount();
}
