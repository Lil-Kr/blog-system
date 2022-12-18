package com.cy.common.model.userserver.pojo.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @Author: Lil-K
 * @Date: 2022/12/11
 * @Description:
 */
@Data
@ToString
public class AdminLoginParam {

    @NotNull(message = "login_account不能为空")
    @JsonProperty("login_account")
    private String loginAccount;

    @NotNull(message = "password不能为空")
    @NotBlank
    private String password;

}