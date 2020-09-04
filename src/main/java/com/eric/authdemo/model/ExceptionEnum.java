package com.eric.authdemo.model;

import com.eric.authdemo.constant.ExceptionConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Eric
 * @since 2020/9/4 4:07 下午
 */
@Getter
@AllArgsConstructor
public enum ExceptionEnum {
    /**
     * OK
     */
    XXX_EXCEPTION(ExceptionConstants.XXX_EXCEPTION, "XXX_EXCEPTION异常");

    private int code;
    private String msg;

    public static String getMsg(int code) {
        for (ExceptionEnum c : ExceptionEnum.values()) {
            if (c.getCode() == code) {
                return c.msg;
            }
        }
        return null;
    }
}
