package com.cy.sys.dao.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.sys.pojo.sys.entity.SysAcl;
import com.cy.sys.pojo.sys.param.acl.AclPageParam;
import com.cy.sys.pojo.sys.vo.acl.SysAclVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lil-K
 * @since 2020-11-24
 */
public interface SysAclMapper extends BaseMapper<SysAcl> {

    IPage<SysAclVo> selectAclListPage(Page<SysAclVo> page, @Param("param") AclPageParam param);

    List<SysAcl> selectAclListByAclIdList(@Param("userAclIdList") List<Long> userAclIdList);
}
