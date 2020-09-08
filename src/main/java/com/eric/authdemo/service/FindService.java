package com.eric.authdemo.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.springframework.jdbc.BadSqlGrammarException;

/**
 * @author Eric
 * @since 2020/8/26 4:46 下午
 */
public interface FindService {

    /**
     * 普通查询
     *
     * @param column    字段名
     * @param condition 条件
     * @param clazz     类名
     * @return 返回字段对应的数据
     * @throws BadSqlGrammarException 错误的SQL异常
     */
    Object query(String column, String condition, Class<?> clazz) throws BadSqlGrammarException;

    /**
     * Lambda查询
     *
     * @param column 字段名
     * @param <T>    泛型
     * @return 返回字段对应的数据
     * @throws NoSuchFieldException 异常
     */
    <T> Object lambdaQuery(SFunction<T, ?> column) throws NoSuchFieldException;
}
