package com.example.springboot.config;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot.enums.ResultCode;
import com.example.springboot.exception.APIException;

import com.example.springboot.vo.ResultVO;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author RudeCrab
 * @description 全局异常处理
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(APIException.class)
    public ResultVO<String> APIExceptionHandler(APIException e) {
        return new ResultVO<>(ResultCode.FAILED, e.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);

        // 然后提取错误提示信息进行返回
        return new ResultVO<>(ResultCode.VALIDATE_FAILED,objectError.getDefaultMessage());
    }
    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        // 得到请求URL地址时使用的方法
        String method = request.getMethod();
        // 定义一个返回的json对象(你们要返回什么自己来，把上面的返回值改一下就行了)
        JSONObject json = new JSONObject();
        if (e instanceof HttpRequestMethodNotSupportedException) {
            json.put("status", 0);
            json.put("msg", method + "请求");
        }
        return json;
    }

}
