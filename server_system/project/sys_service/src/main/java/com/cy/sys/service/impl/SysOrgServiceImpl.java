package com.cy.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.common.utils.dateUtil.DateUtil;
import com.cy.common.utils.keyUtil.IdWorker;
import com.cy.sys.common.holder.RequestHolder;
import com.cy.sys.dao.SysOrgMapper;
import com.cy.sys.pojo.dto.org.OrgLevelDto;
import com.cy.sys.pojo.entity.SysOrg;
import com.cy.sys.pojo.param.org.OrgDeleteParam;
import com.cy.sys.pojo.param.org.OrgGetChildrenParam;
import com.cy.sys.pojo.param.org.OrgListAllParam;
import com.cy.sys.pojo.param.org.OrgParam;
import com.cy.sys.service.ISysOrgService;
import com.cy.sys.util.org.LevelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author CY
 * @since 2020-11-25
 */
@Service
@Slf4j
public class SysOrgServiceImpl extends ServiceImpl<SysOrgMapper, SysOrg> implements ISysOrgService {

    @Resource
    private SysOrgMapper sysOrgMapper1;

    @Resource
    private SysTreeService sysTreeService1;

    /**
     * 添加部门信息
     * @param param
     * @return
     */
    @Override
    public ApiResp add(OrgParam param) throws Exception {
        /**
         * 检查部门名是否相同
         */
        if (checkorgExist(param.getParentSurrogateId(),param.getName(),param.getSurrogateId())) {// 检查
            return ApiResp.failure("待添加的部门名不能重复");
        }

        /**计算层级**/
        String level = LevelUtil.calculateLevel(getLevel(param.getParentId()),param.getParentId());
        Long surrogateId = IdWorker.getsnowFlakeId(); // surrogateId
        String currentTime = DateUtil.getNowDateTime();// 当前时间
        SysOrg org = SysOrg.builder()
                .surrogateId(surrogateId)
                .number("org"+ surrogateId)
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

        sysOrgMapper1.insert(org);
        return ApiResp.success("添加成功");
    }

    /**
     * 更新部门信息
     * @param param
     * @return
     */
    @Override
    public ApiResp edit(OrgParam param) throws Exception {
        if (checkorgExist(param.getParentSurrogateId(),param.getName(),param.getSurrogateId())) {// 检查部门名是否重复
            return ApiResp.failure("待更新的部门名不能重复");
        }

        // 检查待更新的部门是否存在
        SysOrg before = sysOrgMapper1.selectById(param.getId());
        if (Objects.isNull(before)) {
            return ApiResp.failure("待更新的部门不存在");
        }

        // 更新当前部门
        SysOrg after = SysOrg.builder()
                .id(before.getId())
                .surrogateId(before.getSurrogateId())
                .name(param.getName())
                .parentId(param.getParentSurrogateId())// 上级部门id
                .seq(param.getSeq())
                .level(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()))
                .remark(param.getRemark())
                .updateTime(DateUtil.getNowDateTime())
                .operator(RequestHolder.getCurrentUser().getLoginAccount())
                .operateIp("127.0.0.1")
                .build();

        // 更新子部门信息
        this.updateWithChildorg(before,after);
        return ApiResp.success("更新部门成功");
    }

    /**
     * 更新当前部门的子部门信息
     * @param before 旧部门
     * @param after 新部门
     */
    @Transactional
    protected void updateWithChildorg(SysOrg before, SysOrg after) {
        // 修改当前部门信息
        sysOrgMapper1.updateById(after);
        // 更新当前部门的子部门
        String newLevelPrefix = after.getLevel();// 0.1.3
        String oldLevelPrefix = before.getLevel();// 0.1
        if (!newLevelPrefix.equals(oldLevelPrefix)) {// 不一致需要做子部门的更新
            this.updateChildorgTree(after);
        }
    }

    /**
     * 递归变更部门树层级, 并维护子部门的level
     */
    protected void updateChildorgTree(SysOrg afterorg) {
        List<SysOrg> orgList = sysOrgMapper1.selectChildorgListByParentId(afterorg.getSurrogateId());
        if (CollectionUtils.isEmpty(orgList)) {
            return;
        }

        orgList.forEach(org -> {
            org.setLevel(LevelUtil.calculateLevel(afterorg.getLevel(),afterorg.getId()));
            org.setUpdateTime(DateUtil.getNowDateTime());
            updateChildorgTree(org);
        });
        // 操作db
        this.updateBatchById(orgList);
    }

    /**
     * 获取当前部门信息及子部门信息
     * @param dto
     * @return
     */
    @Override
    public ApiResp getChildrenorgList(OrgGetChildrenParam dto) throws Exception {
        // 当前部门
        QueryWrapper<SysOrg> query1 = new QueryWrapper<>();
        query1.eq("surrogate_id",dto.getSurrogateId());
        SysOrg org = sysOrgMapper1.selectOne(query1);

        // 子部门
        QueryWrapper<SysOrg> query2 = new QueryWrapper<>();
        List<SysOrg> orgChildrenList = sysOrgMapper1.selectList(query2);
        orgChildrenList.add(org);

        // 排序
        Collections.sort(orgChildrenList, com.cy.sys.util.org.OrgUtil.orgByIdComparator);
        return ApiResp.success(orgChildrenList);
    }

    /**
     * 获取部门树信息
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp orgTree() throws Exception {
        List<OrgLevelDto> dtoList = sysTreeService1.orgTree();
        return ApiResp.success(dtoList);
    }

    /**
     * 删除部门
     * @param param
     * @return
     */
    @Override
    public ApiResp delete(OrgDeleteParam param) throws Exception {
        QueryWrapper<SysOrg> query = new QueryWrapper<>();
        query.eq("surrogate_id", param.getSurrogateId());
        SysOrg org = sysOrgMapper1.selectOne(query);
        if (Objects.isNull(org)) {
            return ApiResp.failure("待删除的部门不存在");
        }

        // 检查要删除的部门下面是否还有子部门
        QueryWrapper<SysOrg> query1 = new QueryWrapper<>();
        query1.eq("parent_id", param.getSurrogateId());
        Integer count = sysOrgMapper1.selectCount(query1);
        if (count >= 1) {
            return ApiResp.failure("待删除的部门下存在子部门, 不能删除");
        }

        sysOrgMapper1.deleteById(org.getId());
        return ApiResp.success("删除部门成功");
    }

    /**
     * 查询所有部门信息
     * @return
     */
    @Override
    public ApiResp orgListAll(OrgListAllParam param) throws Exception {
        QueryWrapper<SysOrg> query1 = new QueryWrapper<>();
        if (Objects.nonNull(param.getNumber())) {
            query1.like("number",param.getNumber());
        }
        if (Objects.nonNull(param.getName())) {
            query1.like("name",param.getName());
        }
        query1.orderByAsc("surrogate_id");// 排序
        List<SysOrg> orgList = sysOrgMapper1.selectList(query1);
        Collections.sort(orgList, com.cy.sys.util.org.OrgUtil.orgComparator);
        return ApiResp.success(orgList);
    }

    /**
     * 分页查询部门信息
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp orgListPage(OrgListAllParam param) throws Exception {
        Page<SysOrg> page = new Page<>(param.getCurrent(), param.getSize());
        QueryWrapper<SysOrg> queryPage = new QueryWrapper<>();
        if (Objects.nonNull(param.getNumber())) {
            queryPage.like("number",param.getNumber());
        }
        if (Objects.nonNull(param.getName())) {
            queryPage.like("name",param.getName());
        }
        IPage<SysOrg> iPage = sysOrgMapper1.selectPage(page, queryPage);
        return ApiResp.success(iPage);
    }

    /**
     * 校验同一级部门下不能有名称重复的部门
     * @param parentId
     * @param orgName
     * @param SurrogateId
     * @return true/false
     */
    private boolean checkorgExist(Long parentId,String orgName,Long SurrogateId) {
        QueryWrapper<SysOrg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",parentId);
        if (Objects.nonNull(orgName)) {
            queryWrapper.eq("name",orgName);
        }
        if (Objects.nonNull(SurrogateId)) {
            queryWrapper.eq("surrogate_id",SurrogateId);
        }
        Integer count = sysOrgMapper1.selectCount(queryWrapper);
        if (count >= 1) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 获取当前部门所在层级的level
     * @param orgId
     * @return
     */
    private String getLevel(Long orgId) {
        SysOrg org = sysOrgMapper1.selectById(orgId);
        if (Objects.isNull(org)) {
            return null;
        }else {
            return org.getLevel();
        }
    }

}
