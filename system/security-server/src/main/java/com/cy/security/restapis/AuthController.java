package com.cy.security.restapis;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Lil-K
 * @Date: 2022/12/19
 * @Description:
 */
@RestController
@RequestMapping("admin")
public class AuthController {

    @GetMapping("test1")
    public ResponseEntity<String> test1 () throws Exception {
        return ResponseEntity.ok("auth-test1");
    }

    @PostMapping("login")
    public ResponseEntity<String> login () throws Exception {
        return ResponseEntity.ok("auth-login");
    }
}
