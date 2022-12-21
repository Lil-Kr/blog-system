package com.cy.user.servic.impl;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.common.utils.dateUtil.DateUtil;
import com.cy.common.utils.keyUtil.IdWorker;
import com.cy.common.model.userserver.pojo.entity.User;
import com.cy.common.model.userserver.pojo.param.UserDelParam;
import com.cy.common.model.userserver.pojo.param.UserListPageParam;
import com.cy.common.model.userserver.pojo.param.UserSaveParam;
import com.cy.common.model.userserver.pojo.param.UserUpdatePwdParam;
import com.cy.common.model.userserver.pojo.vo.UserVo;
import com.cy.user.common.constant.InterceptorName;
import com.cy.user.common.constant.UserInfoConst;
import com.cy.user.dao.UserMapper;
import com.cy.user.servic.UserService;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author Lil-K
 * @since 2020-11-26
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper1;

    /**
     * 分页查询
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp<IPage> listPage(UserListPageParam param) throws Exception {
        Page<UserVo> page = new Page<>(param.getCurrent(), param.getSize());
        page.setCurrent(param.getCurrent());
        page.setSize(param.getSize());
        IPage<UserVo> iPage = userMapper1.selectUserPage(page, param);
        return ApiResp.success(iPage);
    }

    /**
     * 获取所有用户信息
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp<List<User>> listAll() throws Exception {
        List<User> users = userMapper1.selectList(new QueryWrapper<>());

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
    public ApiResp<String> updatePassword(UserUpdatePwdParam param) throws Exception {

        // 检查旧密码是否一致
        QueryWrapper<User> query1 = new QueryWrapper<>();
        query1.eq("surrogate_id",param.getSurrogateId());
        query1.eq("login_account",param.getLoginAccount());
        query1.eq("password",SecureUtil.md5(param.getOldPassword()));
        User user = userMapper1.selectOne(query1);
        if (Objects.isNull(user)) {
            return ApiResp.error("用户不存在");
        }

        if (!SecureUtil.md5(param.getOldPassword()).equals(user.getPassword())) {
            return ApiResp.error("用户旧密码不正确");
        }

        Integer updatePwd = userMapper1.updatePasswordById(param);
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
    public ApiResp<String> add(UserSaveParam param) throws Exception {

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

        User user = User.builder().build();
        BeanUtils.copyProperties(param,user);

        Long surrogateId = IdWorker.getsnowFlakeId();

        user.setSurrogateId(surrogateId);
        user.setNumber("USER"+surrogateId);
        user.setPassword(SecureUtil.md5(UserInfoConst.password));
        String currentTime = DateUtil.getNowDateTime();
        user.setCreateTime(currentTime);
        user.setUpdateTime(currentTime);
        // 新增用户
        userMapper1.insert(user);

        // TODO 邮件通知用户修改密码

        return ApiResp.success("添加用户成功");
    }

    /**
     * 根据关键字查询
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp<User> findByLoginAccount(String loginAccount) throws Exception {
        QueryWrapper<User> query1 = new QueryWrapper<>();
        query1.eq(InterceptorName.login_account, loginAccount);
        User user = userMapper1.selectOne(query1);
        return checkUserExist(user);
    }

    @Override
    public ApiResp<User> findByLoginAccountAndPwd(String loginAccount,String password) throws Exception {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq(InterceptorName.login_account, loginAccount);
        query.eq(InterceptorName.password, password);
        User user = userMapper1.selectOne(query);
        return checkUserExist(user);
    }

    /**
     * 编辑用户信息
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp<String> edit(UserSaveParam param) throws Exception {

        // 检查手机号是否有相同的用户
        if (checkTelExist(param.getTelephone(),param.getSurrogateId())) {
            return ApiResp.error("待添加的用户手机号已存在");
        }
        // 检查Email是否有相同的用户
        if (checkEmailExist(param.getMail(),param.getSurrogateId())) {
            return ApiResp.error("待添加的用户邮箱已存在");
        }

        QueryWrapper<User> query1 = new QueryWrapper<>();
        query1.eq("surrogate_id",param.getSurrogateId());
        User before = userMapper1.selectOne(query1);
        Preconditions.checkNotNull(before, "待更新的用户不存在");

        User user = User.builder().build();
        BeanUtils.copyProperties(param,user);
        user.setUpdateTime(DateUtil.getNowDateTime());

        UpdateWrapper<User> update1 = new UpdateWrapper<>();
        update1.eq("surrogate_id",param.getSurrogateId());
        userMapper1.update(user,update1);
        return ApiResp.success("更新用户信息成功");
    }

    /**
     * 删除用户
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResp<String> delete(UserDelParam param) throws Exception {

        User user = User.builder()
                .deleted(1) // 删除状态
                .build();
        UpdateWrapper<User> update1 = new UpdateWrapper<>();
        update1.eq("surrogate_id",param.getSurrogateId());
        int update = userMapper1.update(user, update1);
        if (update >= 1) {
            return ApiResp.success("删除用户成功");
        }else {
            return ApiResp.error("删除用户失败", JSON.toJSONString(param));
        }
    }

    /**
     * 检查手机号是否有相同的用户
     * @param telephone
     * @param surrogateId
     * @return
     */
    protected boolean checkTelExist(String telephone, Long surrogateId) {
        QueryWrapper<User> query1 = new QueryWrapper<>();
        query1.eq("telephone",telephone);
        query1.eq("surrogate_id",surrogateId);
        Integer count = userMapper1.selectCount(query1);
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
        QueryWrapper<User> query1 = new QueryWrapper<>();
        query1.eq("email",email);
        query1.eq("surrogate_id",surrogateId);
        Integer count = userMapper1.selectCount(query1);
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
        QueryWrapper<User> query1 = new QueryWrapper<>();
        query1.eq("login_account",account);
        query1.eq("surrogate_id",surrogateId);
        Integer count = userMapper1.selectCount(query1);
        if (count >= 1) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 检查用户是否存在
     * @param user
     * @return
     */
    private ApiResp<User> checkUserExist(User user) {
        if (Objects.isNull(user)) {
            return ApiResp.error("用户名或密码错误");
        }else {
            return ApiResp.success(user);
        }
    }

}
