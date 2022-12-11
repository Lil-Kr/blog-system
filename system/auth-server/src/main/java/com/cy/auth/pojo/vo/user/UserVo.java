package com.cy.auth.pojo.vo.user;

import com.cy.auth.pojo.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVo extends User {
}
