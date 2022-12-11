package com.cy.auth.pojo.vo.org;

import com.cy.auth.pojo.entity.Org;
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
public class OrgVO extends Org {

}
