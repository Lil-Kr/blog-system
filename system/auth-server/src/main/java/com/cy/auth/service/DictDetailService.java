package com.cy.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.auth.pojo.entity.DictDetail;
import com.cy.auth.pojo.param.dict.DictListPageParam;
import com.cy.auth.pojo.param.dict.DictSaveDetailParam;

/**
 * <p>
 *  字典明细服务类
 * </p>
 * @author Lil-K
 * @since 2020-11-29
 */
public interface DictDetailService extends IService<DictDetail> {

    ApiResp addDetail(DictSaveDetailParam param) throws Exception;

    ApiResp editDetail(DictSaveDetailParam param) throws Exception;

    ApiResp deleteDetail(Long surrogateId) throws Exception;

    ApiResp listDetailPage(DictListPageParam param) throws Exception;
}