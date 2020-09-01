package com.eric.authdemo.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Eric
 * @since 2020/5/19 23:45
 */
@Data
@TableName(value = "t_user")
public class User {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否锁定 0:未锁定 1:锁定
     */
    private int lockFlag;

    /**
     * 是否可用 0:可用 1:不可用
     */
    private int enableFlag;
}