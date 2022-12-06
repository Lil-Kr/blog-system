package com.cy.sys.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.common.utils.dateUtil.DateUtil;
import com.cy.common.utils.keyUtil.IdWorker;
import com.cy.sys.common.constant.InterceptorName;
import com.cy.sys.common.constant.UserInfoConst;
import com.cy.sys.dao.SysUserMapper;
import com.cy.sys.pojo.entity.SysUser;
import com.cy.sys.pojo.param.user.UserDelParam;
import com.cy.sys.pojo.param.user.UserListPageParam;
import com.cy.sys.pojo.param.user.UserSaveParam;
import com.cy.sys.pojo.param.user.UserUpdatePwdParam;
import com.cy.sys.pojo.vo.user.SysUserVo;
import com.cy.sys.service.ISysUserService;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author CY
 * @since 2020-11-26
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    private SysUserMapper sysUserMapper1;

    /**
     * 分页查询
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp listPage(UserListPageParam param) throws Exception {
        Page<SysUserVo> page = new Page<>(param.getCurrent(), param.getSize());
        page.setCurrent(param.getCurrent());
        page.setSize(param.getSize());
        IPage<SysUserVo> iPage = sysUserMapper1.selectUserPage(page, param);
        return ApiResp.success(iPage);
    }

    /**
     * 获取所有用户信息
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp listAll() throws Exception {
        List<SysUser> users = sysUserMapper1.selectList(new QueryWrapper<>());

        if (CollectionUtils.isEmpty(users)) {
            return ApiResp.success(Lists.newArrayList());
        }
        return ApiResp.success(users);
    }

    /**
     * 修改用户密码
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp updatePassword(UserUpdatePwdParam param) throws Exception {

        // 检查旧密码是否一致
        QueryWrapper<SysUser> query1 = new QueryWrapper<>();
        query1.eq("surrogate_id",param.getSurrogateId());
        query1.eq("login_account",param.getLoginAccount());
        query1.eq("password",SecureUtil.md5(param.getOldPassword()));
        SysUser user = sysUserMapper1.selectOne(query1);
        if (Objects.isNull(user)) {
            return ApiResp.error("用户不存在");
        }

        if (!SecureUtil.md5(param.getOldPassword()).equals(user.getPassword())) {
            return ApiResp.error("用户旧密码不正确");
        }

        Integer updatePwd = sysUserMapper1.updatePasswordById(param);
        if (updatePwd >=1) {
            return ApiResp.success("修改用户密码成功, 请重新登录");
        }else {
            return ApiResp.failure("修改用户密码失败");
        }
    }

    /**
     * 增加用户信息
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp add(UserSaveParam param) throws Exception {

        // 检查注册登录账号是否有相同的
        if (checkAccountExist(param.getLoginAccount(),param.getSurrogateId())) {
            return ApiResp.failure("待添加的用户账号已存在");
        }

        // 检查手机号是否有相同的用户
        if (checkTelExist(param.getTelephone(),param.getSurrogateId())) {
            return ApiResp.failure("待添加的用户手机号已存在");
        }

        // 检查Email是否有相同的用户
        if (checkEmailExist(param.getMail(),param.getSurrogateId())) {
            return ApiResp.failure("待添加的用户邮箱已存在");
        }

        SysUser user = SysUser.builder().build();
        BeanUtils.copyProperties(param,user);

        Long surrogateId = IdWorker.getsnowFlakeId();

        user.setSurrogateId(surrogateId);
        user.setNumber("USER"+surrogateId);
        user.setPassword(SecureUtil.md5(UserInfoConst.password));
        String currentTime = DateUtil.getNowDateTime();
        user.setCreateTime(currentTime);
        user.setUpdateTime(currentTime);
        // 新增用户
        sysUserMapper1.insert(user);

        // TODO 邮件通知用户修改密码

        return ApiResp.success("添加用户成功");
    }

    /**
     * 根据关键字查询
     * @return
     * @throws Exception
     */
    @Override
    public SysUser findByLoginAccount(String loginAccount) throws Exception {
        QueryWrapper<SysUser> query1 = new QueryWrapper<>();
        query1.eq(InterceptorName.login_account, loginAccount);
        SysUser user = sysUserMapper1.selectOne(query1);
        return user;
    }

    /**
     * 编辑用户信息
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp edit(UserSaveParam param) throws Exception {

        // 检查手机号是否有相同的用户
        if (checkTelExist(param.getTelephone(),param.getSurrogateId())) {
            return ApiResp.error("待添加的用户手机号已存在");
        }
        // 检查Email是否有相同的用户
        if (checkEmailExist(param.getMail(),param.getSurrogateId())) {
            return ApiResp.error("待添加的用户邮箱已存在");
        }

        QueryWrapper<SysUser> query1 = new QueryWrapper<>();
        query1.eq("surrogate_id",param.getSurrogateId());
        SysUser before = sysUserMapper1.selectOne(query1);
        Preconditions.checkNotNull(before, "待更新的用户不存在");

        SysUser user = SysUser.builder().build();
        BeanUtils.copyProperties(param,user);
        user.setUpdateTime(DateUtil.getNowDateTime());

        UpdateWrapper<SysUser> update1 = new UpdateWrapper<>();
        update1.eq("surrogate_id",param.getSurrogateId());
        sysUserMapper1.update(user,update1);
        return ApiResp.success("更新用户信息成功");
    }

    /**
     * 删除用户
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp delete(UserDelParam param) throws Exception {

        SysUser user = SysUser.builder()
                .deleted(1) // 删除状态
                .build();
        UpdateWrapper<SysUser> update1 = new UpdateWrapper<>();
        update1.eq("surrogate_id",param.getSurrogateId());
        int update = sysUserMapper1.update(user, update1);
        if (update >= 1) {
            return ApiResp.success("删除用户成功");
        }else {
            return ApiResp.error("删除用户失败", JSONObject.toJSONString(param));
        }
    }

    /**
     * 检查手机号是否有相同的用户
     * @param telephone
     * @param surrogateId
     * @return
     */
    protected boolean checkTelExist(String telephone, Long surrogateId) {
        QueryWrapper<SysUser> query1 = new QueryWrapper<>();
        query1.eq("telephone",telephone);
        query1.eq("surrogate_id",surrogateId);
        Integer count = sysUserMapper1.selectCount(query1);
        if (count >= 1) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 检查Email是否有相同的用户
     * @param email
     * @param surrogateId
     * @return
     */
    protected boolean checkEmailExist(String email, Long surrogateId) {
        QueryWrapper<SysUser> query1 = new QueryWrapper<>();
        query1.eq("email",email);
        query1.eq("surrogate_id",surrogateId);
        Integer count = sysUserMapper1.selectCount(query1);
        if (count >= 1) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 检查用户的账号是否有相同的账号
     * @param account 登录账号
     * @param surrogateId 主键id
     * @return
     */
    protected boolean checkAccountExist(String account, Long surrogateId) {
        QueryWrapper<SysUser> query1 = new QueryWrapper<>();
        query1.eq("login_account",account);
        query1.eq("surrogate_id",surrogateId);
        Integer count = sysUserMapper1.selectCount(query1);
        if (count >= 1) {
            return true;
        }else {
            return false;
        }
    }



}
