package com.eric.authdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eric.authdemo.model.domain.Permission;
import com.eric.authdemo.model.dto.PermissionDTO;

import java.util.List;

/**
 * @author Eric
 * @since 2020/8/26 4:46 下午
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 获取全部的权限信息.包含角色
     *
     * @return 角色集合
     */
    List<PermissionDTO> findAll();
}
