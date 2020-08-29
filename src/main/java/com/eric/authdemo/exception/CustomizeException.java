package com.eric.authdemo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Eric
 * @date 2019/12/15 0:13
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomizeException extends RuntimeException {
    private String msg;
    private int code;
}
