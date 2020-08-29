package com.eric.authdemo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Eric
 * @since 2020/5/21 2:17 下午
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TokenException extends RuntimeException {
    private String msg;
}
