package com.cy.security.service.impl;

import com.cy.common.model.userserver.pojo.entity.User;
import com.cy.security.feignclient.UserServiceFeignClient;
import com.cy.security.pojo.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lil-K
 * @Date: 2022/12/20
 * @Description:
 */
@Service
public class CustomUserServiceImpl implements UserDetailsService {

    private static String loginAccount = "cy";
    private static String password = "e10adc3949ba59abbe56e057f20f883e";

    @Autowired
    private UserServiceFeignClient userServiceFeignClient;


    /**
     * 这里根据传进来的用户账号进行用户信息的构建
     * 通常的做法是
     *  1 根据username查询数据库对应的用户信息
     *  2 根据用户信息查询出用户权限信息  例如菜单添加权限  sys:menu:add
     *  3 根据用户账号,密码,权限构建对应的UserDetails对象返回
     * 这里实际上是没有进行用户认证功能的,真正的验证是在UsernamePasswordAuthenticationFilter对象当中
     * UsernamePasswordAuthenticationFilter对象会自动根据前端传入的账号信息和UserDetails对象对比进行账号的验证
     * 通常情况下,已经满足常见的使用场景,不过如果有特殊需求,需要使用自己实现的具体认证方式,可以继承UsernamePasswordAuthenticationFilter对象
     * 重写attemptAuthentication 方法和successfulAuthentication方法
     * 最后在WebSecurityConfiguration里面添加自己的过滤器即可
     * @param loginAccount 用户账号
     * @return UserInfo
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String loginAccount) throws UsernameNotFoundException {
        /**
         * todo:
         * 模拟数据库中存在这个用户
         */
//        ApiResp resp = userServiceFeignClient.userInfo(loginAccount);
//        if (Objects.isNull(resp) || Objects.isNull(resp.getData())) {
//            throw new UsernameNotFoundException("account or password error");
//        }
//        User userInfo = (User)resp.getData();

        if (!this.loginAccount.equals(loginAccount)) {
            throw new UsernameNotFoundException("account or password error");
        }
        User userInfo = User.builder().id(1l).loginAccount(loginAccount).password(this.password).userName("陈羽").build();

        /**
         * todo:
         * 模拟当前用户所拥有的权限
         */
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("超级管理员");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return new UserInfo(userInfo, authorities);
    }

    public static void main(String[] args) {
//        float i = 6.36f;
//        System.out.println(1f / 2f);
//        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);

        int n[] = {0,1,2,3,5};
        int sum = 0;

        for (int i=1; i<6;i++) {
            sum = sum + n[i];
        }
        System.out.println(sum);


    }

}
