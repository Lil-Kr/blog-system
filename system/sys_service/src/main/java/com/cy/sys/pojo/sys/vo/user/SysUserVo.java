package com.cy.sys.pojo.sys.vo.user;

import com.cy.sys.pojo.sys.entity.SysUser;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysUserVo extends SysUser {
}
