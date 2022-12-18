package com.cy.auth.util;

import cn.hutool.crypto.digest.DigestUtil;

/**
 * @Author: Lil-K
 * @Date: 2022/12/18
 * @Description:
 */
public class Demo {
    public static void main(String[] args) {
        String s = DigestUtil.md5Hex("123456");
        System.out.println(s);

    }
}
