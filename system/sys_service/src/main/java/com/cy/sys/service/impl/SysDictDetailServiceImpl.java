package com.cy.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.common.utils.keyUtil.IdWorker;
import com.cy.sys.dao.SysDictDetailMapper;
import com.cy.sys.pojo.entity.SysDictDetail;
import com.cy.sys.pojo.param.dict.DictListPageParam;
import com.cy.sys.pojo.param.dict.DictSaveDetailParam;
import com.cy.sys.service.ISysDictDetailService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CY
 * @since 2020-11-29
 */
@Service
@Slf4j
public class SysDictDetailServiceImpl extends ServiceImpl<SysDictDetailMapper, SysDictDetail> implements ISysDictDetailService {


    @Resource
    private SysDictDetailMapper sysDictDetailMapper1;

    /**
     * 新增字典类型明细
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp addDetail(DictSaveDetailParam param) throws Exception {
        if (checkDetailExist(param.getSurrogateId(),param.getName())) {
            ApiResp.failure("待新增的字典类型明细已存在");
        }

        Long surrogateId = IdWorker.getsnowFlakeId(); // surrogateId
        SysDictDetail dictDetail = SysDictDetail.builder()
                .surrogateId(surrogateId)
                .parentId(param.getParentId())
                .name(param.getName())
                .remark(param.getRemark())
                .build();
        sysDictDetailMapper1.insert(dictDetail);
        return ApiResp.success("新增字典明细成功");
    }

    /**
     * 修改字典类型明细
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp editDetail(DictSaveDetailParam param) throws Exception {
        if (checkDetailExist(param.getSurrogateId(),param.getName())) {
            ApiResp.failure("待新增的字典类型明细已存在");
        }
        QueryWrapper<SysDictDetail> query = new QueryWrapper<>();
        query.eq("surrogate_id",param.getSurrogateId());
        SysDictDetail before = sysDictDetailMapper1.selectOne(query);
        Preconditions.checkNotNull(before, "待更新的字典明细信息不存在");

        SysDictDetail after = SysDictDetail.builder()
                .surrogateId(param.getSurrogateId())
                .parentId(param.getParentId())
                .name(param.getName())
                .remark(param.getRemark())
                .build();
        int update = sysDictDetailMapper1.update(after, query);
        if (update >= 1) {
            return ApiResp.success("修改字典明细信息成功");
        }else {
            return ApiResp.failure("修改字典明细信息失败");
        }
    }

    /**
     * 删除字典明细
     * @param surrogateId
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp deleteDetail(Long surrogateId) throws Exception {
        QueryWrapper<SysDictDetail> query = new QueryWrapper<>();
        query.eq("surrogate_id",surrogateId);
        int delete = sysDictDetailMapper1.delete(query);
        if (delete >= 1) {
            return ApiResp.success("删除字典明细信息成功");
        }else {
            return ApiResp.failure("删除字典明细信息失败");
        }
    }

    /**
     * 根据字典主表分页查询字典明细数据
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp listDetailPage(DictListPageParam param) throws Exception {
        Page<SysDictDetail> page = new Page<>();
        page.setCurrent(param.getCurrent());
        page.setSize(param.getSize());
        IPage<SysDictDetail> iPage = sysDictDetailMapper1.listDetailPage(page,param);

        return ApiResp.success(iPage);
    }

    /**
     * 检查是否存在相同的明细名称
     * @param surrogateId
     * @param name
     * @return
     */
    protected boolean checkDetailExist(Long surrogateId,String name) {
        QueryWrapper<SysDictDetail> query = new QueryWrapper<>();
        if (Objects.nonNull(surrogateId)) {
            query.eq("surrogate_id",surrogateId);
        }
        query.eq("name",name);
        Integer count = sysDictDetailMapper1.selectCount(query);
        if (count >= 1) {
            return true;
        }else {
            return false;
        }
    }

}
