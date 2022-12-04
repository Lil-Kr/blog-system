package com.cy.sys.util.user;

import com.cy.common.utils.dateUtil.DateUtil;

import java.util.Random;

/**
 * 用户相关工具类
 */
public class UserUtil {

    public final static String[] word = {
            "a", "b", "c", "d", "e", "f", "g", "h", "j", "k", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    public final static String[] number = {
            "2", "3", "4", "5", "6", "7", "8", "9"
    };

    /**
     * 随机生成密码
     * @return
     */
    public static String randomPassword(){
        StringBuffer sb = new StringBuffer();
        Random random = new Random(DateUtil.getCurrentDateTimeMilli());
        boolean flag = false;
        int length = random.nextInt(3) + 8;
        for (int i = 0; i < length; i++) {
            if (flag){
                sb.append(number[random.nextInt(number.length)]);
            }else {
                sb.append(word[random.nextInt(word.length)]);
            }

            flag = !flag;
        }
        return sb.toString();
    }
}
