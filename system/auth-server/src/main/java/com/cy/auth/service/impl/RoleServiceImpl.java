package com.cy.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.auth.dao.RoleUserMapper;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.common.utils.dateUtil.DateUtil;
import com.cy.common.utils.keyUtil.IdWorker;
import com.cy.auth.common.holder.RequestHolder;
import com.cy.auth.dao.RoleMapper;
import com.cy.auth.pojo.entity.Role;
import com.cy.auth.pojo.param.role.RoleListPageParam;
import com.cy.auth.pojo.param.role.RoleSaveParam;
import com.cy.auth.pojo.vo.role.RoleVo;
import com.cy.auth.service.RoleService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.apache.commons.collections4.CollectionUtils;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  角色服务实现类
 * </p>
 *
 * @author Lil-K
 * @since 2020-11-26
 */
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper1;

    @Resource
    private RoleUserMapper roleUserMapper;

    /**
     * 添加角色信息
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp add(RoleSaveParam param) throws Exception {
        if (checkExit(param.getSurrogateId(),param.getName())) {
            return ApiResp.failure("角色名已存在");
        }

        Long surrogateId = IdWorker.getsnowFlakeId(); // surrogateId
        String currentTime = DateUtil.getNowDateTime();// 当前时间
        Role role = Role.builder()
                .surrogateId(surrogateId)
                .name(param.getName())
                .type(param.getType())
                .deleted(0)
                .remark(param.getRemark())
                .operator(RequestHolder.getCurrentUser().getLoginAccount())
                .operateIp("127.0.0.1")
                .createTime(currentTime)
                .updateTime(currentTime)
                .build();

        roleMapper1.insert(role);
        return ApiResp.success("添加角色信息成功");
    }

    /**
     * 修改角色信息
     * @param param
     * @throws Exception
     */
    @Override
    public ApiResp edit(RoleSaveParam param) throws Exception {

        QueryWrapper<Role> query = new QueryWrapper<>();
        query.eq("surrogate_id",param.getSurrogateId());
        Role before = roleMapper1.selectOne(query);
        if (Objects.isNull(before)) {
            return ApiResp.failure("待更新的角色信息不存在");
        }

        Role after = Role.builder()
                .id(before.getId())
                .surrogateId(param.getSurrogateId())
                .name(param.getName())
                .type(param.getType())
                .operator(RequestHolder.getCurrentUser().getLoginAccount())
                .remark(param.getRemark())
                .operateIp("127.0.0.1")
                .updateTime(DateUtil.getNowDateTime())
                .build();

        int update = roleMapper1.update(after, query);
        if (update >= 1) {
            return ApiResp.success("修改角色信息成功");
        }else {
            return ApiResp.failure("修改角色信息失败");
        }
    }

    /**
     * 分页查询角色列表
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp listPage(RoleListPageParam param) throws Exception {
        Page<RoleVo> page = new Page<>();
        page.setCurrent(param.getCurrent());
        page.setSize(param.getSize());

        IPage<RoleVo> iPage = roleMapper1.selectRoleListPage(page, param);
        return ApiResp.success(iPage);
    }

    /**
     * 根据类型姓名检查同一类型下是否有相同名称的角色
     * @param surrogateId 父id
     * @param name 角色名称
     */
    protected boolean checkExit(Long surrogateId,String name) {
        QueryWrapper<Role> query = new QueryWrapper<>();
        if (Objects.nonNull(surrogateId)) {
            query.eq("surrogate_id",surrogateId);
        }
        query.eq("name",name);
        Integer count = roleMapper1.selectCount(query);
        if (count >= 1) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 删除角色信息
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp delete(RoleSaveParam param) throws Exception {
        QueryWrapper<Role> query = new QueryWrapper();
        query.eq("surrogate_id",param.getSurrogateId());
        Role role = roleMapper1.selectOne(query);
        if (Objects.isNull(role)) {
            return ApiResp.failure("角色不存在, 删除失败");
        }

        //修改删除状态
        role.setDeleted(1);
        role.setUpdateTime(DateUtil.getNowDateTime());
        int update = roleMapper1.updateById(role);
        if (update >= 1) {
            return ApiResp.success("删除角色成功");
        }else {
            return ApiResp.failure("删除角色失败");
        }
    }

    /**
     * 获取所有角色信息
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp listAll(RoleListPageParam param) throws Exception {
        QueryWrapper<Role> query1 = new QueryWrapper<>();
        query1.orderByAsc("create_time");
        List<Role> roleList = roleMapper1.selectList(query1);
        return ApiResp.success(roleList);
    }

    /**
     * 根据userId获取角色信息
     * @return
     */
    @Override
    public List<Role> getRoleListByUserId (long userId) {
        List<Long> roleIdList = roleUserMapper.selectRoleIdListByUserId(userId);
        if (CollectionUtils.isEmpty(roleIdList)) {
            return Lists.newArrayList();
        }
        QueryWrapper<Role> query = new QueryWrapper<>();
        query.in("surrogate_id",roleIdList);
        List<Role> roleList = roleMapper1.selectList(query);
        return roleList;
    }
}
