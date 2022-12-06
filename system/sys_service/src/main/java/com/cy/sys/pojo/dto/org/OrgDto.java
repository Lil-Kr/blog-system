package com.cy.sys.pojo.dto.org;

import com.cy.sys.pojo.entity.SysOrg;
import com.cy.sys.pojo.param.org.OrgParam;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

@Slf4j
@Data
@ToString
public class OrgDto extends SysOrg {

    /**
     * 参数转换为实体类
     * @param param
     * @return
     */
    public static SysOrg paramToSysorg(OrgParam param) {
        SysOrg org = SysOrg.builder().build();
        BeanUtils.copyProperties(param,org);
        return org;
    }

    public static SysOrg paramToSysorg(SysOrg param) {
        SysOrg org = SysOrg.builder().build();
        BeanUtils.copyProperties(param,org);
        return org;
    }
}
