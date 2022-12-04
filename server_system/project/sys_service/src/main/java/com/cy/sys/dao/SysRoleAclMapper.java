package com.cy.sys.dao;

import com.cy.sys.pojo.entity.SysRoleAcl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CY
 * @since 2020-11-24
 */
public interface SysRoleAclMapper extends BaseMapper<SysRoleAcl> {

    List<Long> selectAclIdListByRoleIdList(@Param("userRoleIdList") List<Long> userRoleIdList);

    List<Long> selectAclIdListByRoleId(@Param("roleSurrogateId") Long roleSurrogateId);
}
