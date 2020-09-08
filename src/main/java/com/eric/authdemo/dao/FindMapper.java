package com.eric.authdemo.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author Eric
 * @since 2020/8/26 4:47 下午
 */
public interface FindMapper {

    /**
     * 普通查询
     *
     * @param column 字段名
     * @param table  表名
     * @return 返回字段对应的数据
     */
    Object check(@Param("column") String column, @Param("table") String table);
}
