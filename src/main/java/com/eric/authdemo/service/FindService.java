package com.eric.authdemo.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.springframework.jdbc.BadSqlGrammarException;

/**
 * @author Eric
 * @since 2020/8/26 4:46 下午
 */
public interface FindService<T> {
    T check(SFunction<T, ?> column) throws BadSqlGrammarException;
}
