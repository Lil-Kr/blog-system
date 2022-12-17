package com.cy.gateway.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Lil-K
 * @Date: 2022/12/16
 * @Description:
 */
@RestController
@RequestMapping("/gateway/test")
public class GateWayTestController {

    /**
     * test1
     * @return
     */
    @GetMapping("test1")
    public String test1(){
        return "这是网关-test1";
    }

}
