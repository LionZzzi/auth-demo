package com.eric.authdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eric.authdemo.model.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Eric
 * @since 2020/8/26 4:47 下午
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户ID获取角色
     *
     * @param userId 用户ID
     * @return 角色集合
     */
    List<Role> findByUserId(Long userId);

    /**
     * 根据权限ID获取角色
     *
     * @param permissionId 权限ID
     * @return 角色集合
     */
    List<Role> findByPermissionId(Long permissionId);
}
