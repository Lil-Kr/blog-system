package com.cy.security.config;

import com.cy.common.utils.apiUtil.ApiResp;
import com.cy.security.config.exception.ApiException;
import com.google.common.collect.Maps;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 全局异常处理类
 *
 * @author Lil-Kr
 * @since 2020-11-12
 */
@ControllerAdvice
@Configuration
public class GlobalExceptionHandler {

    /**
     * 验证异常
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiResp validateException(HttpServletRequest request,
                                     MethodArgumentNotValidException exception) throws Exception {
        BindingResult bindingResult = exception.getBindingResult();
        Map errorMesssageMap = Maps.newHashMap();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssageMap.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        return ApiResp.error(errorMesssageMap.toString());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public ApiResp validateException(HttpServletRequest request,
                                     HttpMessageNotReadableException exception) throws Exception {
        String message = exception.getMessage();
        Map errorMesssageMap = Maps.newHashMap();
        errorMesssageMap.put("msg", message);
        return ApiResp.error(errorMesssageMap.toString());
    }


    /**
     * 接口异常
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = ApiException.class)
    @ResponseBody
    public ApiResp validateException(HttpServletRequest request,
                                     ApiException exception) throws Exception{
        return ApiResp.create(exception.getCode(),exception.getMsg(),exception.getData());
    }

    /**
     * 捕捉Controller全局异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResp defaultExceptionHandler(HttpServletRequest req, HttpServletResponse resp, Exception e) throws Exception {
        e.printStackTrace();
        return ApiResp.error(resp.getStatus(), e.getMessage());
    }
}
