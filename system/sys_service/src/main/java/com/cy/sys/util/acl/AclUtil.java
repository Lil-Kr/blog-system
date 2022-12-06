package com.cy.sys.util.acl;

import com.cy.sys.pojo.dto.acl.AclDto;

import java.util.Comparator;

/**
 * @Description:
 * @Author: CY
 * @Date: 2020/12/1
 */
public class AclUtil {

    /**
     * 以AclDto排序, 权限点列表根据seq排序
     */
    public static Comparator<AclDto> aclDtoComparator = new Comparator<AclDto>() {
        @Override
        public int compare(AclDto o1, AclDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };
}
