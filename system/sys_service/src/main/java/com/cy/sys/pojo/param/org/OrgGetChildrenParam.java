package com.cy.sys.pojo.param.org;


import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class OrgGetChildrenParam {

    @NotNull(message = "部门surrogateId不能为空")
    private Long surrogateId;

}
