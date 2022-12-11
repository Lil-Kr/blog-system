package com.cy.auth.util.org;

import com.cy.auth.pojo.dto.org.OrgLevelDto;
import com.cy.auth.pojo.entity.Org;

import java.util.Comparator;

public class OrgUtil {

    /**
     * 以OrgLevelDto排序, 组织列表根据seq排序
     */
    public static Comparator<OrgLevelDto> orgLevelDtoComparator = new Comparator<OrgLevelDto>() {
        @Override
        public int compare(OrgLevelDto o1, OrgLevelDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

    /**
     * 以Org排序
     */
    public static Comparator<Org> orgComparator = new Comparator<Org>() {
        @Override
        public int compare(Org o1, Org o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

    /**
     * 以Org排序
     */
    public static Comparator<Org> orgByIdComparator = new Comparator<Org>() {
        @Override
        public int compare(Org o1, Org o2) {
            return (int) (o1.getId() - o2.getId());
        }
    };
}
