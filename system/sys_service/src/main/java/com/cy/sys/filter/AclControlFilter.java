package com.cy.sys.filter;

import com.alibaba.fastjson.JSONObject;
import com.cy.sys.common.holder.ApplicationContextHelper;
import com.cy.sys.common.holder.RequestHolder;
import com.cy.sys.pojo.entity.SysUser;
import com.cy.sys.service.impl.SysCoreServiceImpl;
import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @Classname AclControlFilter
 * @Auther cy
 * @Date 2020/12/3 21:03
 * @Description 拦截权限
 */
@Slf4j
public class AclControlFilter implements Filter{

    private static Set<String> exclusionUrlSet = Sets.newConcurrentHashSet();

    /**
     * 初始化用户所有的权限点信息
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String exclusionUrls = filterConfig.getInitParameter("exclusionUrls");
        List<String> exclusionUrlList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(exclusionUrls);
        exclusionUrlSet = Sets.newConcurrentHashSet(exclusionUrlList);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String servletPath = request.getServletPath();
        Map<String, String[]> parameterMap = request.getParameterMap();

        if (exclusionUrlSet.contains(servletPath)) {
            filterChain.doFilter(request,response);
            return;
        }

        SysUser user = RequestHolder.getCurrentUser();
        if (Objects.isNull(user)) {
            // 记录日志
            log.info("Someone visit {}, but no login, parameter:{}",servletPath, JSONObject.toJSON(parameterMap));
            // 当没有权限访问时
            noAuth(request, response);
            return;
        }

        SysCoreServiceImpl sysCoreService = ApplicationContextHelper.popBean(SysCoreServiceImpl.class);

    }

    /**
     * 无权限访问时
     * @param request
     * @param response
     */
    private void noAuth(ServletRequest request, ServletResponse response) {

    }


    public boolean hasUrlAcl(String url) {
        return true;
    }

    @Override
    public void destroy() {

    }
}
