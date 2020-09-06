package com.eric.authdemo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.eric.authdemo.dao.FindMapper;
import com.eric.authdemo.service.FindService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Eric
 * @since 2020/8/26 4:46 下午
 */
@Slf4j
@Service
public class FindServiceImpl<T> implements FindService<T> {

    @Resource
    private FindMapper mapper;

    @Override
    public T check(SFunction<T, ?> fn) throws BadSqlGrammarException {
        // TODO: Redis取值操作
        //TableName annotation = clazz.getAnnotation(TableName.class);
//        if (ObjectUtil.isNotNull(annotation)) {
////            mapper.check(name, annotation.value())
//            return null;
//        }
        return null;
    }
}
