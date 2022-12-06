package com.cy.sys.controller;

import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.sys.pojo.param.dict.DictListPageParam;
import com.cy.sys.pojo.param.dict.DictSaveDetailParam;
import com.cy.sys.pojo.param.dict.DictSaveParam;
import com.cy.sys.service.ISysDictDetailService;
import com.cy.sys.service.ISysDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 数据字典管理
 * @author CY
 * @since 2020-11-28
 */
@RestController
@RequestMapping("/sys/dict")
@Slf4j
public class SysDictController {

    @Resource
    private ISysDictService sysDictService1;

    @Resource
    private ISysDictDetailService sysDictDetailService1;

    /**
     * 保存数据字典类型信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("save")
    public ApiResp save (@RequestBody @Valid DictSaveParam param) throws Exception {
        if (Objects.isNull(param.getSurrogateId())) {
            return sysDictService1.add(param);
        }else {
            return sysDictService1.edit(param);
        }
    }

    @PostMapping("add")
    public ApiResp add (@RequestBody @Valid DictSaveParam param) throws Exception {
            return sysDictService1.add(param);
    }

    @PostMapping("edit")
    public ApiResp edit (@RequestBody @Valid DictSaveParam param) throws Exception {
        return sysDictService1.edit(param);
    }

    /**
     * 数据字典列表
     * @return
     * @throws Exception
     */
    @PostMapping("listAll")
    public ApiResp listAll () throws Exception {
        return sysDictService1.listAll();
    }

    /**
     * 字典明细分页查询
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("listDetailPage")
    public ApiResp listDetailPage (@RequestBody @Valid DictListPageParam param) throws Exception {
        return sysDictDetailService1.listDetailPage(param);
    }

    /**
     * 新增字典明细
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("addDetail")
    public ApiResp addDetail (@RequestBody @Valid DictSaveDetailParam param) throws Exception {
        return sysDictDetailService1.addDetail(param);
    }

    /**
     * 新增字典明细
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("editDetail")
    public ApiResp editDetail (@RequestBody @Valid DictSaveDetailParam param) throws Exception {
        return sysDictDetailService1.editDetail(param);
    }

    /**
     * 删除字典明细明细
     * @param surrogateId
     * @return
     * @throws Exception
     */
    @PostMapping("deleteDetail")
    public ApiResp deleteDetail (@Valid @NotNull(message = "surrogateId不能为空") Long surrogateId) throws Exception {
        return sysDictDetailService1.deleteDetail(surrogateId);
    }

}
