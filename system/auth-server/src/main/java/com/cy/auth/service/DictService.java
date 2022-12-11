package com.cy.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.auth.pojo.entity.Dict;
import com.cy.auth.pojo.param.dict.DictSaveParam;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  数据字典服务类
 * </p>
 *
 * @author Lil-K
 * @since 2020-11-29
 */
@Service
public interface DictService extends IService<Dict> {

    ApiResp add(DictSaveParam param) throws Exception;

    ApiResp edit(DictSaveParam param) throws Exception;

    ApiResp listAll() throws Exception;
}
