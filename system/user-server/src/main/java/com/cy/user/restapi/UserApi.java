package com.cy.user.restapi;

import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.user.pojo.entity.User;
import com.cy.user.pojo.param.UserDelParam;
import com.cy.user.pojo.param.UserListPageParam;
import com.cy.user.pojo.param.UserSaveParam;
import com.cy.user.pojo.param.UserUpdatePwdParam;
import com.cy.user.servic.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

/**
 * 用户服务模块
 * @author Lil-K
 * @since 2020-11-26
 */
@RestController
@RequestMapping("/user")
public class UserApi {

    @Autowired
    private UserService userService;

    @GetMapping("userInfo/{loginAccount}/{password}")
    public ApiResp userInfo(@PathVariable String loginAccount, @PathVariable String password) throws Exception {
        User user = userService.findByLoginAccountAndPwd(loginAccount,password);
        if (Objects.isNull(user)) {
            return ApiResp.error("用户不存在");
        }else {
            return ApiResp.success(user);
        }
    }

    /**
     * 分页查询列表
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("listPage")
    public ApiResp listPage(@RequestBody @Valid UserListPageParam param) throws Exception {
        return userService.listPage(param);
    }

    /**
     * 保存用户信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("save")
    public ApiResp save(@RequestBody @Valid UserSaveParam param) throws Exception {

        if (Objects.nonNull(param.getId()) && Objects.nonNull(param.getSurrogateId())) {// update
            return userService.edit(param);
        }else { // insert
            return userService.add(param);
        }
    }

    /**
     * 添加用户信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("add")
    public ApiResp add(@RequestBody @Valid UserSaveParam param) throws Exception {
        return userService.add(param);
    }

    /**
     * 编辑用户信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("edit")
    public ApiResp edit(@RequestBody @Valid UserSaveParam param) throws Exception {
        return userService.edit(param);
    }

    /**
     * 删除用户信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("delete")
    public ApiResp delete(@RequestBody @Valid UserDelParam param) throws Exception {
        return userService.delete(param);
    }

    /**
     * 修改用户密码
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("updatePassword")
    public ApiResp updatePassword(@RequestBody @Valid UserUpdatePwdParam param) throws Exception {
        return userService.updatePassword(param);
    }

}