package com.cy.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.auth.pojo.entity.RoleAcl;
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
public interface RoleAclMapper extends BaseMapper<RoleAcl> {

    List<Long> selectAclIdListByRoleIdList(@Param("userRoleIdList") List<Long> userRoleIdList);

    List<Long> selectAclIdListByRoleId(@Param("roleSurrogateId") Long roleSurrogateId);
}
