package com.eric.authdemo.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author Eric
 * @since 2020/8/26 4:47 下午
 */
public interface FindMapper {
    Object check(@Param("name") String name, @Param("table") String table);
}
