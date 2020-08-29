package com.eric.authdemo.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Eric
 * @since 2020/8/26 5:17 下午
 */
@Data
@TableName(value = "t_role")
public class Role {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 角色名
     */
    private String name;
}
