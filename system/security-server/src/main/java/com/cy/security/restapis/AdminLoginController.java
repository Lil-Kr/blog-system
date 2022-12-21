package com.cy.security.restapis;

import cn.hutool.core.bean.BeanUtil;
import com.cy.common.model.userserver.pojo.param.AdminLoginParam;
import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.common.utils.jwtUtil.JwtTokenUtil;
import com.cy.common.utils.secret.SymmetricEncryptionUtils;
import com.cy.security.common.Constants;
import com.cy.security.config.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Lil-K
 * @Date: 2022/12/19
 * @Description:
 */
@RestController
@RequestMapping("admin")
public class AdminLoginController {

    @GetMapping("test1")
    public ResponseEntity<String> test1 () throws ApiException {
        return ResponseEntity.ok("auth-test1");
    }

    /**
     * 后台系统登录
     * @return
     * @throws Exception
     */
    @PostMapping("login")
    public ApiResp login (@Valid @RequestBody AdminLoginParam param) throws ApiException {
        /**
         * 生成jwt token 并用对称加密算法token 进行加密
         * todo: 用户信息需要加密
         */
        String token = JwtTokenUtil.generatorJwtToken(BeanUtil.beanToMap(param));
        String encryptToken = SymmetricEncryptionUtils.encryptAES(token);
        Map<String, String> resp = new HashMap<>();
        resp.put(Constants.HEADER_ACCESS_TOKEN_KEY,encryptToken);
        /**
         * todo: 保存到数据库 mysql redis
         */

        return ApiResp.success(resp);
    }
}
