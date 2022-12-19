package com.cy.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.auth.dao.RoleUserMapper;
import com.cy.auth.dao.UserMapper;
import com.cy.auth.pojo.entity.User;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.common.utils.dateUtil.DateUtil;
import com.cy.common.utils.keyUtil.IdWorker;
import com.cy.auth.common.holder.RequestHolder;
import com.cy.auth.dao.RoleMapper;
import com.cy.auth.pojo.entity.RoleUser;
import com.cy.auth.pojo.param.roleuser.RoleUserParam;
import com.cy.auth.service.RoleUserService;
import com.cy.auth.service.UserService;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.collections4.CollectionUtils;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lil-K
 * @since 2020-11-26
 */
@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUser> implements RoleUserService {

    @Resource
    private RoleUserMapper roleUserMapper1;

    @Resource
    private RoleMapper roleMapper1;

    @Resource
    private UserMapper userMapper1;

    @Resource
    private UserService sysUserService1;

    /**
     * 维护[角色-用户]关系接口
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp changeRoleUsers(RoleUserParam param) throws Exception {
        // 根据角色id查询分配的用户id
        List<Long> originUserIdList = roleUserMapper1.selectUserIdListByRoleId(param.getRoleId()).stream().collect(Collectors.toList());
        if (CollectionUtils.isEmpty(originUserIdList)) {
            return ApiResp.failure("当前角色未分配用户");
        }

        // 将需要修改的角色id转为 -> list
        List<Long> userIdList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(param.getUserIds())
                .stream()
                .map(roleId -> Long.valueOf(roleId))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(userIdList)) {
            return ApiResp.failure("待更新的用户id为空");
        }
        if (originUserIdList.size() == userIdList.size()) {
            Set<Long> originUserIdSet = Sets.newHashSet(originUserIdList);
            Set<Long> userIdSet = Sets.newHashSet(userIdList);
            originUserIdSet.removeAll(userIdSet);
            if (CollectionUtils.isEmpty(originUserIdSet)) {
                return ApiResp.failure("待更新的用户信息与原来一致");
            }
        }

        // 更新角色-用户信息
        updateRoleUsers(param.getRoleId(),userIdList);
        return ApiResp.success("更新用户角色信息成功");
    }

    /**
     * 更新角色-用户信息
     * @param roleId
     * @param userIdList
     */
    @Transactional
    protected void updateRoleUsers(Long roleId, List<Long> userIdList) {
        // 删除原来角色分配的用户的对应关系数据
        QueryWrapper<RoleUser> delete = new QueryWrapper<>();
        delete.eq("role_id",roleId);
        roleUserMapper1.delete(delete);

        String currentTime = DateUtil.getNowDateTime();
        List<RoleUser> roleUsers = userIdList.stream()
                .map(userId -> {
                    return RoleUser.builder()
                            .surrogateId(IdWorker.getsnowFlakeId())
                            .roleId(roleId)
                            .userId(userId)
                            .operateIp("127.0.0.1")
                            .operator(RequestHolder.getCurrentUser().getLoginAccount())
                            .createTime(currentTime)
                            .updateTime(currentTime)
                            .build();
                })
                .collect(Collectors.toList());

        // 批量更新角色-用户信息
        this.saveBatch(roleUsers);
    }

    /**
     * 角色用户[待选列表-已选列表]
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp roleUserList(RoleUserParam param) throws Exception {

        List<Long> userIdList = roleUserMapper1.selectUserIdListByRoleId(param.getRoleId());

        if (CollectionUtils.isEmpty(userIdList)) {
            return ApiResp.success("没有数据", Lists.newArrayList());
        }

        // 查询角色锁分配之后的用户信息
        QueryWrapper<User> query1 = new QueryWrapper<>();
        query1.in("surrogate_id",userIdList);
        List<User> roleUserList = userMapper1.selectList(query1);

        // 查询所有的用户信息, 筛选出用户待选列表
        QueryWrapper<User> query2 = new QueryWrapper<>();
        query2.eq("status", 0);// 未删除的用户
        query2.eq("deleted",0);// 正常用户信息
        List<User> userAllList = userMapper1.selectList(query2);
        userAllList.removeAll(roleUserList);

        HashMap<String,List<User>> roleUserMap = Maps.newHashMap();
        roleUserMap.put("selectedUserList",roleUserList);
        roleUserMap.put("unSelectedUserList",userAllList);
        return ApiResp.success(roleUserMap);
    }

//    /**
//     * 分页展示角色下分配的用户信息
//     * @param param
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public ApiResp roleUserPageList(RoleUserParam param) throws Exception {
//        // 获取角色分配的用户id
//        List<Long> userIdList = roleUserMapper1.selectUserIdListByRoleId(param.getRoleId());
//
//        if (CollectionUtils.isEmpty(userIdList)) {
//            Page<User> page = new Page<>(param.getCurrent(), param.getSize());
//            page.setRecords(Lists.newArrayList());
//            return ApiResp.failure("没有数据",page);
//        }
//
//        // 根据用户id获取用户信息
//        Page<User> page = new Page<>(param.getCurrent(), param.getSize());
//        QueryWrapper<User> query1 = new QueryWrapper<>();
//        query1.in("surrogate_id",userIdList);
//        Page<User> userPage = userMapper1.selectPage(page, query1);
//
//        return ApiResp.success(userPage);
//    }
}
