package com.cy.security.filter;

import com.alibaba.fastjson2.JSON;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.security.common.Constants;
import com.cy.security.config.properties.SecurityConfigProperties;
import com.cy.security.utils.request.RequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @Author: Lil-K
 * @Date: 2022/12/20
 * @Description: 登录认证
 */
//@Component("customLoginAdminAuthFilter")
public class CustomLoginAdminAuthFilter extends OncePerRequestFilter {

    @Autowired
    private SecurityConfigProperties securityConfigProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        // 获取请求的url
        String requestURI = request.getRequestURI();
        // 获取body参数类型 -> application/json (body 通常为 json)
        String contentType = request.getContentType();
        if (securityConfigProperties.getFilterRequestUrls().contains(requestURI)
                && Constants.CONTENT_TYPE.equals(contentType)) {
            Map<String, Object> bodyParametersFromRequest = RequestUtils.getBodyParametersFromRequest(request);
            String loginAccount = (String)bodyParametersFromRequest.get(Constants.LOGIN_ACCOUNT);
            String password = (String)bodyParametersFromRequest.get(Constants.PASSWORD);
            if (StringUtils.isBlank(loginAccount) || StringUtils.isBlank(password)) {
                PrintWriter out = response.getWriter();

                out.write(JSON.toJSONString(ApiResp.failure("用户名或密码不能为空")));
                out.flush();
                out.close();
                return;
            }
        }

        System.out.println("loginAuthFilter");
        filterChain.doFilter(request,response);
    }
}
