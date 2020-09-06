package com.eric.authdemo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.core.toolkit.support.SerializedLambda;
import com.eric.authdemo.dao.FindMapper;
import com.eric.authdemo.service.FindService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.property.PropertyNamer;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;

/**
 * @author Eric
 * @since 2020/8/26 4:46 下午
 */
@Slf4j
@Service
public class FindServiceImpl implements FindService {

    @Resource
    private FindMapper mapper;

    @Override
    public Object query(String name, Class<?> clazz) throws BadSqlGrammarException {
        // TODO: Redis取值操作
        TableName annotation = clazz.getAnnotation(TableName.class);
        if (ObjectUtil.isNotNull(annotation)) {
            return mapper.check(name, annotation.value());
        }
        return null;
    }

    @Override
    public <T> Object lambdaQuery(SFunction<T, ?> column) throws BadSqlGrammarException, NoSuchFieldException {
        // TODO: Redis取值操作
        // 获取解析后的 SerializedLambda
        SerializedLambda lambda = SerializedLambda.resolve(column);
        // 获取对应的类
        Class<?> clazz = lambda.getInstantiatedType();
        // 通过类获取对应的 TableName 注解信息
        TableName annotation = clazz.getAnnotation(TableName.class);
        if (ObjectUtil.isNotNull(annotation)) {
            // 获取对应的 XX::getAAA 中AAA的字段名
            String fieldName = PropertyNamer.methodToProperty(lambda.getImplMethodName());
            Field declaredField = lambda.getImplClass().getDeclaredField(fieldName);
            // 获取字段上的 TableField 注解信息
            TableField tableField = declaredField.getAnnotation(TableField.class);
            if (ObjectUtil.isNotNull(tableField)) {
                // 取映射 TableField 中的 value 查询
                return mapper.check(tableField.value(), annotation.value());
            } else {
                // 否则类似 teacherName 转化为 teacher_name 进行查询
                // 如果数据库字段是 teacherName 则无需以下操作
                return mapper.check(fieldName.replaceAll("[A-Z]", "_$0").toLowerCase(), annotation.value());
            }
        }
        return null;
    }
}
