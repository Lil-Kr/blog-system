package com.cy.common.model.userserver.api;

import com.cy.common.model.userserver.pojo.entity.User;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.common.model.userserver.pojo.param.UserDelParam;
import com.cy.common.model.userserver.pojo.param.UserListPageParam;
import com.cy.common.model.userserver.pojo.param.UserSaveParam;
import com.cy.common.model.userserver.pojo.param.UserUpdatePwdParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Author: Lil-K
 * @Date: 2022/12/17
 * @Description:
 */
public interface UserServerApi {

    @GetMapping("/userInfo/{loginAccount}/{password}")
    ApiResp userInfo(@PathVariable("loginAccount") String loginAccount, @PathVariable("password") String password) throws Exception;

    @GetMapping("/userInfo/{loginAccount}")
    ApiResp<User> userInfo(@PathVariable("loginAccount") String loginAccount) throws Exception;

    /**
     * 分页查询列表
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("/listPage")
    ApiResp listPage(@RequestBody @Valid UserListPageParam param) throws Exception;

    /**
     * 保存用户信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("/save")
    ApiResp save(@RequestBody @Valid UserSaveParam param) throws Exception;

    /**
     * 添加用户信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("/add")
    ApiResp add(@RequestBody @Valid UserSaveParam param) throws Exception;

    /**
     * 编辑用户信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("/edit")
    ApiResp edit(@RequestBody @Valid UserSaveParam param) throws Exception;

    /**
     * 删除用户信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("/delete")
    ApiResp delete(@RequestBody @Valid UserDelParam param) throws Exception;

    /**
     * 修改用户密码
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("/updatePassword")
    ApiResp updatePassword(@RequestBody @Valid UserUpdatePwdParam param) throws Exception;
}
