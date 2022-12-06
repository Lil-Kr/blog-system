package com.cy.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.sys.pojo.entity.SysOrg;
import org.apache.ibatis.annotations.Mapper;
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
@Mapper
public interface SysOrgMapper extends BaseMapper<SysOrg> {

    List<SysOrg> selectChildorgList(@Param("level") String oldLevelPrefix);

    List<SysOrg> selectChildorgListByParentId(@Param("parentId") Long parentId);
}
