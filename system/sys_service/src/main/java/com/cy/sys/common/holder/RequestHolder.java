package com.cy.sys.common.holder;

import com.cy.sys.pojo.entity.SysUser;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取每个进程独立的 request 和 用户信息
 * @author CY
 * @since 2020-11-28
 */
public class RequestHolder {

    /**
     * 存放线程隔离的用户信息
     * 每个用户登录进来都会分别存放用户信息
     */
    private static final ThreadLocal<SysUser> userHolder = new ThreadLocal<>();

    /**
     * 存放线程隔离的http请求信息
     */
    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<>();

    /**
     * 存放用户信息
     * @param user
     */
    public static void setCurrentUser(SysUser user){
        userHolder.set(user);
    }

    /**
     * 获取线程隔离的用户信息
     * @return SysUser
     */
    public static SysUser getCurrentUser(){
        return userHolder.get();
    }

    /**
     * 存放线程隔离的http请求信息
     * @param request
     */
    public static void setHttpServletRequest(HttpServletRequest request){
        requestHolder.set(request);
    }

    /**
     * 获取线程隔离的http请求信息
     * @return HttpServletRequest
     */
    public static HttpServletRequest getHttpServletRequest(){
        return requestHolder.get();
    }

    /**
     * 用户退出时移除用户信息
     */
    public static void removeUser() {
        userHolder.remove();
    }

    /**
     * 移除 HttpServletRequest
     */
    public static void removeHttpServletRequest() {
        requestHolder.remove();
    }

    /**
     * 同时移除用户信息和 HttpServletRequest
     */
    public static void remove() {
        userHolder.remove();
        requestHolder.remove();
    }

}