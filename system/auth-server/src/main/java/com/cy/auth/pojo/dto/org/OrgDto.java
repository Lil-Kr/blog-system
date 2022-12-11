package com.cy.auth.pojo.dto.org;

import com.cy.auth.pojo.entity.Org;
import com.cy.auth.pojo.param.org.OrgParam;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

@Slf4j
@Data
@ToString
public class OrgDto extends Org {

    /**
     * 参数转换为实体类
     * @param param
     * @return
     */
    public static Org paramToSysOrg(OrgParam param) {
        Org org = Org.builder().build();
        BeanUtils.copyProperties(param,org);
        return org;
    }

    public static Org paramToSysOrg(Org param) {
        Org org = Org.builder().build();
        BeanUtils.copyProperties(param,org);
        return org;
    }
}
