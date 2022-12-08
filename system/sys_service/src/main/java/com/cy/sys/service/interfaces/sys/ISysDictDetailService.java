package com.cy.sys.service.interfaces.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.sys.entity.SysDictDetail;
import com.cy.sys.pojo.sys.param.dict.DictListPageParam;
import com.cy.sys.pojo.sys.param.dict.DictSaveDetailParam;

/**
 * <p>
 *  字典明细服务类
 * </p>
 * @author Lil-K
 * @since 2020-11-29
 */
public interface ISysDictDetailService extends IService<SysDictDetail> {

    ApiResp addDetail(DictSaveDetailParam param) throws Exception;

    ApiResp editDetail(DictSaveDetailParam param) throws Exception;

    ApiResp deleteDetail(Long surrogateId) throws Exception;

    ApiResp listDetailPage(DictListPageParam param) throws Exception;
}