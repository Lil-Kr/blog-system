package com.cy.sys.dao.sys;

import com.cy.sys.pojo.sys.entity.SysAclModule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface SysAclModuleMapper extends BaseMapper<SysAclModule> {

    List<SysAclModule> selectChildAclModuleListByParentId(@Param("parentId") Long surrogateId);
}
