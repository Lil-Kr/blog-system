package com.cy.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.common.utils.dateUtil.DateUtil;
import com.cy.common.utils.keyUtil.IdWorker;
import com.cy.auth.common.holder.RequestHolder;
import com.cy.auth.dao.DictMapper;
import com.cy.auth.pojo.entity.Dict;
import com.cy.auth.pojo.param.dict.DictSaveParam;
import com.cy.auth.service.DictService;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lil-K
 * @since 2020-11-29
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Resource
    private DictMapper dictMapper1;

    /**
     * 新增数据字典分类
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp add(DictSaveParam param) throws Exception {
        if (checkExist(param.getSurrogateId(),param.getType(),param.getName())) {
            ApiResp.failure("待新增的字典类型或类型名已存在");
        }

        Long surrogateId = IdWorker.getsnowFlakeId(); // surrogateId
        String currentTime = DateUtil.getNowDateTime();// 当前时间

        Dict dict = Dict.builder()
                .surrogateId(surrogateId)
                .name(param.getName())
                .remark(param.getRemark())
                .deleted(0)
                .operator(RequestHolder.getCurrentUser().getLoginAccount())
                .operateIp("127.0.0.1")
                .createTime(currentTime)
                .updateTime(currentTime)
                .build();

        dictMapper1.insert(dict);
        return ApiResp.success("添加数据字典信息成功");
    }

    /**
     * 更新数据字典分类
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp edit(DictSaveParam param) throws Exception {
        if (checkExist(param.getSurrogateId(),param.getType(),param.getName())) {
            ApiResp.failure("待新增的字典类型或类型名已存在");
        }

        QueryWrapper<Dict> query = new QueryWrapper<>();
        query.eq("surrogate_id",param.getSurrogateId());
        Dict before = dictMapper1.selectOne(query);
        Preconditions.checkNotNull(before, "待更新的数据字典信息不存在");

        Dict after = Dict.builder()
                .surrogateId(before.getSurrogateId())
                .operator(RequestHolder.getCurrentUser().getLoginAccount())
                .name(param.getName())
                .remark(param.getRemark())
                .deleted(0)
                .operator("system") // todo 操作人 系统时间 ip
                .operateIp("127.0.0.1")
                .updateTime(DateUtil.getNowDateTime())
                .build();

        int update = dictMapper1.update(after, query);
        if (update >= 1) {
            return ApiResp.success("修改数据字典信息成功");
        }else {
            return ApiResp.failure("修改数据字典信息失败");
        }
    }

    /**
     * 数据字典类型列表列表
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp listAll() throws Exception {
        QueryWrapper<Dict> query = new QueryWrapper<>();
        query.orderByAsc("create_time");
        List<Dict> dicts = dictMapper1.selectList(query);
        return ApiResp.success(dicts);
    }

    /**
     * 检查是否有相同类型的数据字典类别
     * @param surrogateId
     * @param type
     * @param name
     */
    protected boolean checkExist(Long surrogateId,Integer type,String name) {
        QueryWrapper<Dict> query = new QueryWrapper<>();
        if (Objects.nonNull(surrogateId)) {
            query.eq("surrogate_id",surrogateId);
        }
        query.eq("name",name);
        Integer count = dictMapper1.selectCount(query);
        if (count >= 1) {
            return true;
        }else {
            return false;
        }
    }

}
