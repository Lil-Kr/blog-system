package com.cy.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.auth.pojo.entity.Org;
import org.apache.ibatis.annotations.Mapper;
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
@Mapper
public interface OrgMapper extends BaseMapper<Org> {

    List<Org> selectChildOrgList(@Param("level") String oldLevelPrefix);

    List<Org> selectChildOrgListByParentId(@Param("parentId") Long parentId);
}
