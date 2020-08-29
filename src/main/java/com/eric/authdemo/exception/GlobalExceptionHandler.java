package com.eric.authdemo.exception;

import com.eric.authdemo.model.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 *
 * @author Eric
 * @since 2019/12/14 23:05
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 数据校验异常处理
     *
     * @param e 异常
     * @return 通用Result
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>(16);
        BindingResult result = e.getBindingResult();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(p -> {
                FieldError fieldError = (FieldError) p;
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                log.warn("数据校验异常 : 对象 = {}, 字段 = {} , 错误信息 = {} ", fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
            });
        }
        return Result.fail(errorMap, "数据校验异常");
    }

    /**
     * 自定义异常处理
     *
     * @param e 异常
     * @return 通用Result
     */
    @ExceptionHandler(CustomizeException.class)
    public Result<String> handleCustomizeException(CustomizeException e) {
        log.warn("自定义异常 : 错误信息 = {} , 错误编码 = {} ", e.getMsg(), e.getCode());
        return Result.fail(e.getMsg(), e.getCode());
    }

    /**
     * TOKEN相关异常处理
     *
     * @param e 异常
     * @return 通用Result
     */
    @ExceptionHandler(TokenException.class)
    public Result<String> handleTokenException(TokenException e) {
        log.warn("TOKEN相关异常 : 错误信息 = {}", e.getMsg());
        return Result.fail(e.getMsg());
    }
}
