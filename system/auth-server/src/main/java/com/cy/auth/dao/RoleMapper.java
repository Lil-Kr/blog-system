package com.cy.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.auth.pojo.entity.Role;
import com.cy.auth.pojo.param.role.RoleListPageParam;
import com.cy.auth.pojo.vo.role.RoleVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lil-K
 * @since 2020-11-24
 */
public interface RoleMapper extends BaseMapper<Role> {

    IPage<RoleVo> selectRoleListPage(Page<RoleVo> page, @Param("param") RoleListPageParam param);
}
