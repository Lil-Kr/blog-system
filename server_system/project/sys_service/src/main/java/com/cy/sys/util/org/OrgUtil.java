package com.cy.sys.util.org;

import com.cy.sys.pojo.dto.org.OrgLevelDto;
import com.cy.sys.pojo.entity.SysOrg;

import java.util.Comparator;

public class OrgUtil {

    /**
     * 以orgLevelDto排序, 部门列表根据seq排序
     */
    public static Comparator<OrgLevelDto> orgLevelDtoComparator = new Comparator<OrgLevelDto>() {
        @Override
        public int compare(OrgLevelDto o1, OrgLevelDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

    /**
     * 以Sysorg排序
     */
    public static Comparator<SysOrg> orgComparator = new Comparator<SysOrg>() {
        @Override
        public int compare(SysOrg o1, SysOrg o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

    /**
     * 以Sysorg排序
     */
    public static Comparator<SysOrg> orgByIdComparator = new Comparator<SysOrg>() {
        @Override
        public int compare(SysOrg o1, SysOrg o2) {
            return (int) (o1.getId() - o2.getId());
        }
    };
}
