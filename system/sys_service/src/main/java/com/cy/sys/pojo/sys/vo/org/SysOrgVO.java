package com.cy.sys.pojo.sys.vo.org;

import com.cy.sys.pojo.sys.entity.SysOrg;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

/**
 * @author Lil-K
 * @since 2020-11-24
 */
@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysOrgVO extends SysOrg {

}
