package com.eric.authdemo.model.common;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 通用返回模板
 *
 * @author Eric
 * @date 2019/12/14 15:30
 */
@Data
public class Result<T> {

    /**
     * 状态码
     */
    private int code;

    /**
     * 当前请求是否成功
     */
    private boolean ret;

    /**
     * 消息
     */
    private String msg;

    /**
     * 数据体
     */
    private T data;

    /**
     * 当前请求时间
     */
    private String time;

    private Result(boolean ret) {
        this.ret = ret;
        this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static <T> Result<T> success(T data, String msg) {
        Result<T> result = new Result<>(true);
        result.data = data;
        result.msg = msg;
        return result;
    }

    public static <T> Result<T> success(String msg) {
        Result<T> result = new Result<>(true);
        result.msg = msg;
        return result;
    }

    public static <T> Result<T> fail(String msg, int code) {
        Result<T> result = new Result<>(false);
        result.msg = msg;
        result.code = code;
        return result;
    }

    public static <T> Result<T> fail(String msg) {
        Result<T> result = new Result<>(false);
        result.msg = msg;
        result.code = 400;
        return result;
    }

    public static <T> Result<T> fail(T data, String msg) {
        Result<T> result = new Result<>(false);
        result.data = data;
        result.msg = msg;
        result.code = 400;
        return result;
    }
}
