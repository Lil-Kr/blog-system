//package com.cy.security.filter;
//
//import com.cy.security.common.Constants;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @Author: Lil-K
// * @Date: 2022/12/20
// * @Description: 校验token的过滤器
// */
//@Component
//@Order(0)
//public class TokenAuthFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        // get header token
//        String jwtToken = request.getHeader(Constants.HEADER_ACCESS_TOKEN_KEY);
//        /**
//         * todo: 生成token返回
//         */
//        if (StringUtils.isBlank(jwtToken)) {
//            System.out.println("abc");
//            filterChain.doFilter(request,response);
////            return;
//        }
//
//        // 放行
//        filterChain.doFilter(request,response);
//    }
//}
