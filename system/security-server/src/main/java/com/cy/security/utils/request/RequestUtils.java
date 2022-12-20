package com.cy.security.utils.request;

import com.alibaba.fastjson2.JSON;
import com.cy.security.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Lil-K
 * @Date: 2022/12/20
 * @Description:
 */
@Slf4j
public class RequestUtils {


    /**
     * 获取请求中的指定字符串参数
     */
    public static final String getStringBodyParameterFromRequest(HttpServletRequest request, String key) {
        String result = "";
        Map<String, Object> map = getBodyParametersFromRequest(request);

        if (map.get(key) != null) {
            result = map.get(key).toString();
        }

        return result;
    }

    /**
     * 获取请求中的所有参数
     */
    public static final Map<String, Object> getBodyParametersFromRequest(HttpServletRequest request) {
        Map<String, Object> map = null;

        if (request != null) {
            // 优先从 request attribute 中读取参数
            map = (Map<String, Object>) request.getAttribute(Constants.PARSED_BODY_KEY);

            // 若从 request attribute 没有读取到参数则从 request 中读取，这样做是因为 request 的流只能读取一次
            if (map == null) {
                map = readParamsFromRequest(request);
                // 读取完以后存入 request attribute
                request.setAttribute(Constants.PARSED_BODY_KEY, map);
            }
        }

        if (MapUtils.isEmpty(map)) {
            map = new HashMap<>(0);
        }

        return map;
    }

    /**
     * 从请求中读取参数，转为 Map
     */
    private static Map<String, Object> readParamsFromRequest(HttpServletRequest request) {
        Map<String, Object> map = null;

        try {
            // 从 request 中读取 Body 数据
            ServletInputStream inputStream = request.getInputStream();
            StringBuilder content = new StringBuilder();
            byte[] b = new byte[1024];
            int lens;
            while ((lens = inputStream.read(b)) > 0) {
                content.append(new String(b, 0, lens));
            }

            // 将字符串转换为 Map 集合
            map = JSON.parseObject(content.toString(), Map.class);
        } catch (Exception e) {
            log.error("Error occurred while read params from request: detail info: {}",e.getMessage());
        }

        return map;
    }
}
