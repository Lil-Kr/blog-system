package com.cy.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.auth.pojo.entity.RoleUser;
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
public interface RoleUserMapper extends BaseMapper<RoleUser> {

    List<Long> selectRoleIdListByUserId(@Param("userSurrogateId") Long userSurrogateId);

    List<Long> selectUserIdListByRoleId(@Param("roleId") Long roleId);
}
