package com.cy.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.sys.pojo.entity.SysDictDetail;
import com.cy.sys.pojo.param.dict.DictListPageParam;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CY
 * @since 2020-11-29
 */
public interface SysDictDetailMapper extends BaseMapper<SysDictDetail> {

    IPage<SysDictDetail> listDetailPage(Page<SysDictDetail> page, @Param("param") DictListPageParam param);
}
