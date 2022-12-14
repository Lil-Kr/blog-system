package com.cy.blog.web.config;

import com.cy.blog.web.config.exception.ApiException;
import com.cy.common.utils.apiUtil.ApiResp;
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
import java.util.Map;

/**
 * 全局异常处理类
 *
 * @author Lil-K
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
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiResp validateException(HttpServletRequest request,
                                     MethodArgumentNotValidException exception) throws Exception {
        BindingResult bindingResult = exception.getBindingResult();
        Map errorMesssageMap = Maps.newHashMap();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssageMap.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        return ApiResp.error(errorMesssageMap.toString());
    }

    @ResponseBody
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
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
    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public ApiResp validateException(HttpServletRequest request,
                                     ApiException exception) throws Exception {
        return ApiResp.create(exception.getCode(),exception.getMsg(),exception.getData());
    }

    /**
     * 捕捉Controller全局异常
     *
     * @param req
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ApiResp defaultExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        e.printStackTrace();
        return ApiResp.error( "网络异常",e.getLocalizedMessage());
    }
}
