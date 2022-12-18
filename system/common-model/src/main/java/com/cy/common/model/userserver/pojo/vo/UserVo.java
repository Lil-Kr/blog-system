package com.cy.common.model.userserver.pojo.vo;

import com.cy.common.model.userserver.pojo.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVo extends User {
}
