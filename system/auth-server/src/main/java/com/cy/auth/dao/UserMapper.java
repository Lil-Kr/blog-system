package com.cy.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.auth.pojo.entity.User;
import com.cy.auth.pojo.param.user.UserListPageParam;
import com.cy.auth.pojo.param.user.UserUpdatePwdParam;
import com.cy.auth.pojo.vo.user.UserVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lil-K
 * @since 2020-11-24
 */
public interface UserMapper extends BaseMapper<User> {

    Integer updatePasswordById(@Param("param") UserUpdatePwdParam param);

    IPage<UserVo> selectUserPage(Page<UserVo> pageInfo, @Param("param") UserListPageParam param);
}
