package com.cy.gateway.filter.global;

import com.alibaba.fastjson2.JSON;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.common.utils.jwtUtil.JwtTokenUtil;
import com.cy.common.utils.strUtil.PathUtil;
import com.cy.gateway.config.SkipAuthUrlProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @Author: Lil-K
 * @Date: 2022/12/17
 * @Description:
 */
@Slf4j
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    private SkipAuthUrlProperties skipAuthUrlProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String curUrl = exchange.getRequest().getURI().getPath();
        if (PathUtil.matches(skipAuthUrlProperties.getSkipUrls(), curUrl)) {
            return chain.filter(exchange);
        }

        /**
         * 1. 拦截token, 并校验token
         */
        String token = exchange.getRequest().getHeaders().getFirst("access-token");
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
        if (StringUtils.isBlank(token)) {
            /**
             * 返回错误提示, 并且转为 转为 bytes
             */
            ApiResp apiResp = ApiResp.errorToken(ApiResp.MSG_ERROR, "token can not be blank");
            byte[] bytes = JSON.toJSONString(apiResp).getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(bytes);
            return response.writeWith(Mono.just(buffer));
        }

        /**
         * 2. 校验 token 合法性
         */
        ApiResp apiResp = JwtTokenUtil.validateToken(token);
        // token 校验失败
        if (apiResp.getCode() != ApiResp.CODE_SUCCESS) {
            // 转为 bytes
            byte[] bytes = JSON.toJSONString(apiResp).getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(bytes);
            return response.writeWith(Mono.just(buffer));
        }

        log.info("网关拦截器 -> 这是合法的 token:{}", token);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 检查当前请求url是否可以直接放过
     * @param currentUrl
     * @return
     */
    private boolean checkUrlSkip(String currentUrl){
        return false;
    }

    public static void main(String[] args) {
//        String requestUrl = "/blog-admin/admin/login";
//        String skipPath = "/blog-admin/admin/**";
//        boolean matches = PathUtil.matches(Arrays.asList(skipPath), requestUrl);
//        boolean matche = PathUtil.matche(skipPath, requestUrl);
//        System.out.println(matche);
//        System.out.println(matches);
//        PathMatcher pathMatcher = new AntPathMatcher();
//        boolean match = pathMatcher.match(skipPath, requestUrl);
//        System.out.println(match);
        String a = "Y3kyOmUxMGFkYzM5NDliYTU5YWJiZTU2ZTA1N2YyMGY4ODNl";
        byte[] b = Base64.getDecoder().decode("Y3kyOmUxMGFkYzM5NDliYTU5YWJiZTU2ZTA1N2YyMGY4ODNl");
        System.out.println(new String(b));
    }
}
