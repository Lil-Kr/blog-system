package com.cy.user.pojo.vo;

import com.cy.user.pojo.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVo extends User {
}
