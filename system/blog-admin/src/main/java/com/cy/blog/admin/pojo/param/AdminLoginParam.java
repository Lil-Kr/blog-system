package com.cy.blog.admin.pojo.param;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: Lil-K
 * @Date: 2022/12/11
 * @Description:
 */
@Data
@ToString
public class AdminLoginParam {

    @NotNull(message = "loginAccount不能为空")
    @NotBlank
    private String loginAccount;

    @NotNull(message = "password不能为空")
    @NotBlank
    private String password;

}
