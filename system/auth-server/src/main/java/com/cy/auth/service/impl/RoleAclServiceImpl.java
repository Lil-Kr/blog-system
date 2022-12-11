package com.cy.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.common.utils.dateUtil.DateUtil;
import com.cy.common.utils.keyUtil.IdWorker;
import com.cy.auth.common.holder.RequestHolder;
import com.cy.auth.dao.RoleAclMapper;
import com.cy.auth.pojo.entity.Acl;
import com.cy.auth.pojo.entity.RoleAcl;
import com.cy.auth.pojo.param.roleacl.RoleAclSaveParam;
import com.cy.auth.service.CoreService;
import com.cy.auth.service.RoleAclService;
import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  角色权限服务实现类
 * </p>
 * @author Lil-K
 * @since 2020-11-26
 */
@Service
@Slf4j
public class RoleAclServiceImpl extends ServiceImpl<RoleAclMapper, RoleAcl> implements RoleAclService {

    @Resource
    private RoleAclMapper roleAclMapper1;

    @Resource
    private CoreService sysCoreService1;

    /**
     * 检查同一个角色下不能分配同一个权限
     * @param roleId
     * @param aclId
     * @return
     */
    protected boolean checkRoleAclExist(Long roleId, Long aclId) {
        QueryWrapper<RoleAcl> query1 = new QueryWrapper<>();
        query1.eq("role_id",roleId);
        query1.eq("acl_id",aclId);
        Integer count = roleAclMapper1.selectCount(query1);
        if (count >= 1) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 更新角色对应的权限点信息
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp changeRoleAcls(RoleAclSaveParam param) throws Exception {
        // 将权多个权限点的id转换为Long的列表
        List<Long> aclIdList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(param.getAclIds())
                .stream()
                .map(aclId -> Long.valueOf(aclId))
                .collect(Collectors.toList());

        // 判断将要修改的权限点是否超过了当前用户所拥有的最大权限范围
        List<Acl> currentUserAclList = sysCoreService1.getCurrentUserAclList();
        Set<Long> currentAclIdSet = currentUserAclList.stream().map(acl -> acl.getSurrogateId()).collect(Collectors.toSet());
        Set<Long> aclIdsSet = Sets.newHashSet(aclIdList);
        aclIdsSet.removeAll(currentAclIdSet);
        if (CollectionUtils.isNotEmpty(aclIdsSet)) {
            return ApiResp.failure("待更新的权限点超过已有权限");
        }

        // 查询角色已经分配的权限点id
        List<Long> originAclIdList = roleAclMapper1.selectAclIdListByRoleId(param.getRoleId());
        if (aclIdList.size() == originAclIdList.size()) {
            Set<Long> originAclIdSet = Sets.newHashSet(originAclIdList);
            Set<Long> aclIdSet = Sets.newHashSet(aclIdList);
            originAclIdSet.removeAll(aclIdSet);
            if (CollectionUtils.isEmpty(originAclIdSet)) {// 待更新的权限点与原来的一致, 不用更新
                return ApiResp.failure("没有需要更新的权限点");
            }
        }

        // 修改需要更新的权限点
        this.updateRoleAcls(param.getRoleId(),aclIdList);
        return ApiResp.success("修改角色对应权限点成功");
    }

    /**
     * 更新权限点
     * @param roleId
     * @param aclIdList
     */
    @Transactional
    public void updateRoleAcls (long roleId,List<Long> aclIdList) {
        if (CollectionUtils.isEmpty(aclIdList)) {
            return;
        }
        // 删除
        QueryWrapper<RoleAcl> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",roleId);
        roleAclMapper1.delete(wrapper);

        String currentTime = DateUtil.getNowDateTime();
        List<RoleAcl> roleAclList = aclIdList.stream()
                .map(aclId -> {
                    return RoleAcl.builder()
                            .surrogateId(IdWorker.getsnowFlakeId())
                            .roleId(roleId)
                            .aclId(aclId)
                            .operator(RequestHolder.getCurrentUser().getLoginAccount())
                            .operateIp("127.0.0.1")
                            .createTime(currentTime)
                            .updateTime(currentTime)
                            .build();
                })
                .collect(Collectors.toList());
        // 批量新增
        this.saveBatch(roleAclList);
    }
}
