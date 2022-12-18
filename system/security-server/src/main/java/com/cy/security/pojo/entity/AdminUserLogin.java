package com.cy.security.pojo.entity;

import com.cy.common.model.userserver.pojo.entity.User;
import com.cy.security.service.AdminUserLoginService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @Author: Lil-K
 * @Date: 2022/12/18
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserLogin implements AdminUserLoginService {

    private User user;

    /**
     * 返回权限信息
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * 返回用户密码
     * @return
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * 返回用户名称
      * @return
     */
    @Override
    public String getUsername() {
        return user.getUserName();
    }

    /**
     * 判断用户账号是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getLoginAccount() {
        return user.getLoginAccount();
    }
}
