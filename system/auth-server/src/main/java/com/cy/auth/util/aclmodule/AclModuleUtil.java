package com.cy.auth.util.aclmodule;

import com.cy.auth.pojo.dto.aclmodule.AclModuleDto;
import com.cy.auth.pojo.entity.AclModule;

import java.util.Comparator;

public class AclModuleUtil {

    /**
     * 以AclModule排序, 组织列表根据seq排序
     */
    public static Comparator<AclModuleDto> aclModuleLevelDtoComparator = new Comparator<AclModuleDto>() {
        @Override
        public int compare(AclModuleDto o1, AclModuleDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

    /**
     * 根据顺序(seq)AclModule排序
     */
    public static Comparator<AclModule> aclModuleComparator = new Comparator<AclModule>() {
        @Override
        public int compare(AclModule o1, AclModule o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

    /**
     * 根据Id AclModule排序
     */
    public static Comparator<AclModule> aclModuleByIdComparator = new Comparator<AclModule>() {
        @Override
        public int compare(AclModule o1, AclModule o2) {
            return (int) (o1.getId() - o2.getId());
        }
    };
}
