package com.cy.sys.config.exception;

/**
 * @Description: 接口异常
 */
public class ApiException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String msg;//错误消息
    private int code;//错误代码
    private Object data;//错误数据

    private ApiException(){
        super();
    }

    public ApiException(String msg) {
        super(msg);
        this.msg = msg;
        this.code = -1819;
        this.data = null;
    }

    public ApiException(int code, String msg, Object data) {
        super(msg);
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public ApiException(int code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
        this.data=null;
    }

    public ApiException(Exception e){
        super(e.getMessage());
        if(e instanceof ApiException){
            ApiException self = (ApiException) e;
            this.msg = self.getMsg();
        }else{
            this.msg = e.getMessage();
        }

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
