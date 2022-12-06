package com.cy.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.sys.pojo.entity.SysRoleUser;
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
public interface SysRoleUserMapper extends BaseMapper<SysRoleUser> {

    List<Long> selectRoleIdListByUserId(@Param("userSurrogateId") Long userSurrogateId);

    List<Long> selectUserIdListByRoleId(@Param("roleId") Long roleId);
}
