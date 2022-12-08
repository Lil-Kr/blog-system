package com.cy.sys.dao.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.sys.pojo.sys.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.sys.pojo.sys.param.role.RoleListPageParam;
import com.cy.sys.pojo.sys.vo.role.SysRoleVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lil-K
 * @since 2020-11-24
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    IPage<SysRoleVo> selectRoleListPage(Page<SysRoleVo> page, @Param("param") RoleListPageParam param);
}
