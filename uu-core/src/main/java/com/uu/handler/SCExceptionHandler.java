package com.uu.handler;


import com.uu.exception.SCException;
import com.uu.result.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Optional;

/**
* @Description: 异常处理器
* @Author: liang_qing
* @Email: clarence_liang@163.com
* @Date: 2021/6/8 下午12:47
* @Return
**/
@Slf4j
@RestControllerAdvice
public class SCExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(SCException.class)
    public R handleRRException(SCException e) {
        R r = new R();
        r.put("code", e.getCode());
        r.put("msg", e.getMessage());

        return r;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public R handlerNoFoundException(Exception e) {
        return R.error(404, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        if (!e.getBindingResult().getAllErrors().isEmpty()) {
            Optional<String> message = e.getBindingResult().getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst();
            return R.error(message.get());
        }
        return R.error();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return R.error(e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R httpMessageNotReadableException(HttpMessageNotReadableException e) {
        return R.error(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public R runtimeException(RuntimeException e) {
        e.printStackTrace();

        if(StringUtils.isNotBlank(e.getMessage()) && e.getMessage().contains("ClientException")){
            return R.error("服务正在维护，请稍等片刻 ^_^");
        }else{
            return R.error("系统运行异常！");
        }
    }


    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error();
    }
}
