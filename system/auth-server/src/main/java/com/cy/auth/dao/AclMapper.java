package com.cy.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.auth.pojo.entity.Acl;
import com.cy.auth.pojo.param.acl.AclPageParam;
import com.cy.auth.pojo.vo.acl.AclVo;
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
public interface AclMapper extends BaseMapper<Acl> {

    IPage<AclVo> selectAclListPage(Page<AclVo> page, @Param("param") AclPageParam param);

    List<Acl> selectAclListByAclIdList(@Param("userAclIdList") List<Long> userAclIdList);
}
