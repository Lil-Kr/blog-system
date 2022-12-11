package com.cy.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.auth.pojo.entity.DictDetail;
import com.cy.auth.pojo.param.dict.DictListPageParam;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lil-K
 * @since 2020-11-29
 */
public interface DictDetailMapper extends BaseMapper<DictDetail> {

    IPage<DictDetail> listDetailPage(Page<DictDetail> page, @Param("param") DictListPageParam param);
}
