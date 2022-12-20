package com.cy.common.utils.apiUtil;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

/**
 * API 响应体
 * @author Lil-Kr
 * @date 2018/6/8
 */
@Data
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public final class ApiResp<T> {
    /** 错误编码 */
    public static final int CODE_ERROR = -1;
    /**
     * token校验失败错误码
     */
    public static final int CODE_ERROR_TOKEN = 401;

    public static final int CODE_ERROR_TOKEN_EXPIRED = 403;

    /** 失败编码 */
    public static final int CODE_FAILURE = 1;

    /** 成功编码 */
    public static final int CODE_SUCCESS = 0;

//    /**状态码*/
//    @JsonProperty(value = "state_code")
//    private int stateCode;

    /**响应码*/
    private int code;

    /**响应信息**/
    private String msg;

    /**响应数据**/
    private T data;

    /**
     * 创建响应体
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static ApiResp create(int code, String msg, Object data) {
        return new ApiResp(code,msg,data);
    }

    /**
     * 成功
     * @param msg
     * @return
     */
    public static ApiResp success(String msg) {
        return create(CODE_SUCCESS,msg,null);
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static ApiResp success(Object data) {
        return create(CODE_SUCCESS,"SUCCESS",data);
    }

    /**
     * 成功
     * @param msg
     * @param data
     * @return
     */
    public static ApiResp success(String msg, Object data) {
        return create(CODE_SUCCESS,msg,data);
    }

    /**
     * 失败
     * @param msg
     * @param data
     * @return
     */
    public static ApiResp failure(String msg, Object data) {
        return create(CODE_FAILURE,msg,data);
    }

    /**
     * 失败
     * @param msg
     * @return
     */
    public static ApiResp failure(String msg) {
        return create(CODE_FAILURE,msg,null);
    }


    /**
     * 错误
     * @param msg
     * @param data
     * @return
     */
    public static ApiResp error(String msg, Object data) {
        return create(CODE_ERROR,msg, data);
    }

    /**
     * 错误
     * @param msg
     * @return
     */
    public static ApiResp error(String msg) {
        return create(CODE_ERROR,msg, null);
    }

    public static ApiResp error(int code, String msg) {
        return create(code,msg, null);
    }

    /**
     * token 相关error
     * @param code
     * @param msg
     * @return
     */
    public static ApiResp errorToken(int code, String msg) {
        return create(code, msg, null);
    }

    public static ApiResp errorToken(int code, String msg, Object data) {
        return create(code, msg, data);
    }

    public ApiResp(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
