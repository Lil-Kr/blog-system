package com.cy.sys.dao;

import com.cy.sys.pojo.entity.SysAclModule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface SysAclModuleMapper extends BaseMapper<SysAclModule> {

    List<SysAclModule> selectChildAclModuleListByParentId(@Param("parentId") Long surrogateId);
}
