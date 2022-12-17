package com.cy.security.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Lil-K
 * @Date: 2022/12/15
 * @Description:
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("test1")
    public String test1() {
        return "test1";
    }

}
