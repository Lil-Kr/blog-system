package com.cy.common.utils.jwtUtil;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cy.common.utils.apiUtil.ApiResp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @Author: Lil-K
 * @Date: 2022/12/15
 * @Description:
 */
@Slf4j
public class JwtTokenUtil {

    /**
     * Issuer
     */
    private static final String ISSUER = "blog-system";

    /**
     * Audience: 接收方的一种标识
     */
    private static final String AUDIENCE = "all-front";

    /**
     * 签名密钥 secret_key
     */
    private static final String SECRET_KEY = "35b83335-e932-483c-9640-981f8b4cf0bc";

    /**
     * 算法
     */
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(JwtTokenUtil.SECRET_KEY);

    /**
     * Header
     */
    private static final Map<String, Object> HEADER_MAP = new HashMap<String, Object>() {
        {
            put("alg", "HS256");
            put("typ", "JWT");
        }
    };

    /**
     * 时间对象
     */
    private static Calendar calendar = new GregorianCalendar();

    /**
     * generatorJwtToken
     * @return
     */
    public static String generatorJwtToken() {
        Date nowDate = new Date();
        /**
         * 120 分钟过期
         */
        Date expireDate = JwtTokenUtil.AddDate(nowDate, 2 * 60);
        JWTCreator.Builder tokenBuilder = JWT.create();

        /**
         * token
         * 格式: head.payload.singurater
         * iss: jwt签发者;
         * sub: jwt所面向的用户;
         * aud: 接收jwt的一方;
         * exp: jwt的过期时间, 这个过期时间必须要大于签发时间;
         * nbf: 定义在什么时间之前, 该jwt都是不可用的;
         * iat: jwt的签发时间;
         * jti: jwt的唯一身份标识，主要用来作为一次性token, 从而回避重放攻击;
         */
        String token = tokenBuilder
                //Header 部分
                .withHeader(JwtTokenUtil.HEADER_MAP)
                // payload, 一般存储前端带过来的用户信息
                .withClaim("user_name", "admin")
                //issuer jwt 签发主体
                .withIssuer(JwtTokenUtil.ISSUER)
                //audience
                .withAudience(JwtTokenUtil.AUDIENCE)
                //isa 签发时间(生效时间)
                .withIssuedAt(nowDate)
                //eat 过期时间
                .withExpiresAt(expireDate)
//                .withExpiresAt(DateTime.now().plusMillis(4000).toDate())
                //签名, 算法加密
                .sign(JwtTokenUtil.ALGORITHM);
        return token;
    }

//    public static void main(String[] args) throws InterruptedException {
//        String token = generatorJwtToken();
//        System.out.println(token);
//        Thread.sleep(3000);
//        validateToken(token);
//    }

    /**
     *
     * @param token
     * @return
     */
    public static ApiResp validateToken(String token) {
        if (StringUtils.isBlank(token)) {
            new Exception("access_token 不能为空");
        }

        try {
            DecodedJWT decodedJWT = JWT.require(JwtTokenUtil.ALGORITHM).build().verify(token);
            String name = decodedJWT.getClaim("user_name").asString();
            return ApiResp.success("token 验证成功",name);
        } catch (TokenExpiredException e){
            return ApiResp.errorToken(ApiResp.CODE_ERROR_TOKEN_EXPIRED,"token 已经过期",e.getMessage());
        } catch (Exception e){
            return ApiResp.errorToken(ApiResp.CODE_ERROR_TOKEN,"token 验证失败", e.getMessage());
        }
    }

    public static String get(String token, String key) {
        List<String> list= JWT.decode(token).getAudience();
        String userId = JWT.decode(token).getAudience().get(0);
        return userId;
    }

    /**
     * 设置token时间长度, 校验是否过期
     * @param date
     * @param minute
     * @return
     */
    private static Date AddDate(Date date, Integer minute) {
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }
}
