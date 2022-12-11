package com.cy.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.common.utils.dateUtil.DateUtil;
import com.cy.common.utils.keyUtil.IdWorker;
import com.cy.sys.common.holder.RequestHolder;
import com.cy.sys.dao.SysAclModuleMapper;
import com.cy.sys.pojo.dto.aclmodule.AclModuleDto;
import com.cy.sys.pojo.entity.SysAclModule;
import com.cy.sys.pojo.param.aclmodule.AclModuleDelParam;
import com.cy.sys.pojo.param.aclmodule.AclModuleParam;
import com.cy.sys.service.ISysAclModuleService;
import com.cy.sys.util.org.LevelUtil;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  权限模块服务实现类
 * </p>
 * @author CY
 * @since 2020-11-26
 */
@Service
@Slf4j
public class SysAclModuleServiceImpl extends ServiceImpl<SysAclModuleMapper, SysAclModule> implements ISysAclModuleService {

    @Resource
    private SysAclModuleMapper sysAclModuleMapper1;

    @Resource
    private SysTreeService sysTreeService1;

    /**
     * 添加权限模块信息
     * @param param
     * @return
     */
    @Override
    public ApiResp addAclModule(AclModuleParam param) throws Exception {
        /**
         * 检查权限模块名是否相同
         */
        if (checkAclModuleExist(param.getParentSurrogateId(),param.getName(),param.getSurrogateId())) {
            return ApiResp.failure("待添加的权限模块名不能重复");
        }

        /**计算层级**/
        String level = LevelUtil.calculateLevel(getLevel(param.getParentId()),param.getParentId());
        Long surrogateId = IdWorker.getsnowFlakeId(); // surrogateId
        String currentTime = DateUtil.getNowDateTime();// 当前时间
        SysAclModule aclModule = SysAclModule.builder()
                .surrogateId(surrogateId)
                .number("ACLM"+ surrogateId)
                .parentId(param.getParentSurrogateId())
                .seq(param.getSeq())
                .level(level)
                .name(param.getName())
                .remark(param.getRemark())
                .createTime(currentTime)
                .updateTime(currentTime)
                .operator(RequestHolder.getCurrentUser().getLoginAccount())
                .operateIp("127.0.0.1")
                .build();
        sysAclModuleMapper1.insert(aclModule);
        return ApiResp.success("添加权限模块成功");
    }

    /**
     * 修改权限模块信息
     * @param param
     * @return
     */
    @Override
    public ApiResp editAclModule(AclModuleParam param) throws Exception {
        /**
         * 检查权限模块名是否相同
         */
        if (checkAclModuleExist(param.getParentSurrogateId(),param.getName(),param.getSurrogateId())) {
            return ApiResp.failure("待修改的权限模块名不能重复");
        }

        // 检查待更新的权限模块是否存在
        QueryWrapper<SysAclModule> query1 = new QueryWrapper();
        query1.eq("surrogate_id",param.getSurrogateId());
        SysAclModule before = sysAclModuleMapper1.selectOne(query1);
        Preconditions.checkNotNull(before, "待更新的权限模块不存在");

        // 更新当前的权限模块
        SysAclModule after = SysAclModule.builder()
                .id(before.getId())
                .surrogateId(before.getSurrogateId())
                .name(param.getName())
                .parentId(param.getParentSurrogateId())
                .seq(param.getSeq())
                .level(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()))
                .remark(param.getRemark())
                .updateTime(DateUtil.getNowDateTime())
                .operator(RequestHolder.getCurrentUser().getLoginAccount())
                .operateIp("127.0.0.1")
                .build();

        // 更新子组织信息
        this.updateWithChildAclModule(before,after);
        return ApiResp.success("更新权限模块成功");
    }

    /**
     * 获取模块权限树
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp aclModuleTree() throws Exception {
        List<AclModuleDto> aclModuleDtoList = sysTreeService1.aclModuleTree();
        return ApiResp.success(aclModuleDtoList);
    }

    /**
     * 更新当前权限模块的子权限模块信息
     * @param before 旧权限模块
     * @param after 新权限模块
     */
    @Transactional
    protected void updateWithChildAclModule(SysAclModule before, SysAclModule after) {
        // 修改当前权限模块
        sysAclModuleMapper1.updateById(after);

        // 更新当前权限模块的子权限模块
        String newLevelPrefix = after.getLevel();// 0.1.3
        String oldLevelPrefix = before.getLevel();// 0.1
        if (!newLevelPrefix.equals(oldLevelPrefix)) {// 不一致需要做子组织的更新
            this.updateChildAclModuleTree(after);
        }
    }

    /**
     * 递归变更组织树层级, 并维护子组织的level
     */
    protected void updateChildAclModuleTree(SysAclModule afterAclModule) {
        // 查询当前组织的子组织
        List<SysAclModule> aclModuleList = sysAclModuleMapper1.selectChildAclModuleListByParentId(afterAclModule.getSurrogateId());

        if (CollectionUtils.isEmpty(aclModuleList)) {
            return;
        }

        aclModuleList.forEach(aclModule -> {
            aclModule.setLevel(LevelUtil.calculateLevel(afterAclModule.getLevel(),afterAclModule.getId()));
            aclModule.setUpdateTime(DateUtil.getNowDateTime());
            updateChildAclModuleTree(aclModule);
        });
        // 操作db
        this.updateBatchById(aclModuleList);
    }

    /**
     * 获取当前权限模块所在层级的level
     * @param id
     * @return
     */
    private String getLevel(Long id) {
        SysAclModule aclModule = sysAclModuleMapper1.selectById(id);
        if (Objects.isNull(aclModule)) {
            return null;
        }else {
            return aclModule.getLevel();
        }
    }

    /**
     * 检查部权限模块是否存在
     * @param parentId
     * @param aclModuleName
     * @param SurrogateId
     * @return true/false
     */
    protected boolean checkAclModuleExist(Long parentId,String aclModuleName,Long SurrogateId){
        QueryWrapper<SysAclModule> query1 = new QueryWrapper<>();
        query1.eq("parent_id",parentId);
        if (Objects.nonNull(aclModuleName)) {
            query1.eq("name",aclModuleName);
        }
        if (Objects.nonNull(SurrogateId)) {
            query1.eq("surrogate_id",SurrogateId);
        }
        Integer count = sysAclModuleMapper1.selectCount(query1);
        if (count >= 1) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 删除权限模块信息
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp delete(AclModuleDelParam param) throws Exception {
        QueryWrapper<SysAclModule> query = new QueryWrapper<>();
        query.eq("surrogate_id",param.getSurrogateId());
        SysAclModule aclModule = sysAclModuleMapper1.selectOne(query);
        if (Objects.isNull(aclModule)) {
            return ApiResp.failure("待删除的权限模块不存在");
        }

        // 检查要删除的权限模块下是否还有子权限模块
        QueryWrapper<SysAclModule> query1 = new QueryWrapper<>();
        query1.eq("parent_id", param.getSurrogateId());
        Integer count = sysAclModuleMapper1.selectCount(query1);
        if (count >= 1) {
            return ApiResp.failure("待删除的权限模块还存在子权限模块, 无法删除");
        }

        sysAclModuleMapper1.deleteById(aclModule.getId());
        return ApiResp.success("删除权限模块成功");
    }
}
