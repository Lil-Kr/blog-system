package com.cy.sys.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.sys.pojo.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.sys.pojo.param.role.RoleListPageParam;
import com.cy.sys.pojo.vo.role.SysRoleVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CY
 * @since 2020-11-24
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    IPage<SysRoleVo> selectRoleListPage(Page<SysRoleVo> page, @Param("param") RoleListPageParam param);
}
