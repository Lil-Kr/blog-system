package com.cy.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.entity.SysDictDetail;
import com.cy.sys.pojo.param.dict.DictListPageParam;
import com.cy.sys.pojo.param.dict.DictSaveDetailParam;

/**
 * <p>
 *  字典明细服务类
 * </p>
 * @author CY
 * @since 2020-11-29
 */
public interface ISysDictDetailService extends IService<SysDictDetail> {

    ApiResp addDetail(DictSaveDetailParam param) throws Exception;

    ApiResp editDetail(DictSaveDetailParam param) throws Exception;

    ApiResp deleteDetail(Long surrogateId) throws Exception;

    ApiResp listDetailPage(DictListPageParam param) throws Exception;
}