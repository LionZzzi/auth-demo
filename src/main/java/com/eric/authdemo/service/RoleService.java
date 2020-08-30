package com.eric.authdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eric.authdemo.model.domain.Role;

import java.util.List;

/**
 * @author Eric
 * @since 2020/8/26 4:46 下午
 */
public interface RoleService extends IService<Role> {

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
