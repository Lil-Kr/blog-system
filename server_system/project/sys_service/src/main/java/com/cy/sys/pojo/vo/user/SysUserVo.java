package com.cy.sys.pojo.vo.user;

import com.cy.sys.pojo.entity.SysUser;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysUserVo extends SysUser {
}
