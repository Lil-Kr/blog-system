package com.cy.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.sys.pojo.entity.SysUser;
import com.cy.sys.pojo.param.user.UserListPageParam;
import com.cy.sys.pojo.param.user.UserUpdatePwdParam;
import com.cy.sys.pojo.vo.user.SysUserVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CY
 * @since 2020-11-24
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    Integer updatePasswordById(@Param("param") UserUpdatePwdParam param);

    IPage<SysUserVo> selectUserPage(Page<SysUserVo> pageInfo, @Param("param") UserListPageParam param);
}
