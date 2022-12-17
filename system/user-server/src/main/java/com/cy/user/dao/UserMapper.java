package com.cy.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.downstream.model.userserver.pojo.entity.User;
import com.cy.downstream.model.userserver.pojo.param.UserListPageParam;
import com.cy.downstream.model.userserver.pojo.param.UserUpdatePwdParam;
import com.cy.downstream.model.userserver.pojo.vo.UserVo;
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
