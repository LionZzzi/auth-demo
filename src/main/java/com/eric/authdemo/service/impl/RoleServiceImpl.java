package com.eric.authdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eric.authdemo.dao.RoleMapper;
import com.eric.authdemo.model.domain.Role;
import com.eric.authdemo.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Eric
 * @since 2020/8/26 4:46 下午
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public List<Role> findByUserId(Long userId) {
        return baseMapper.findByUserId(userId);
    }

    @Override
    public List<Role> findByPermissionId(Long permissionId) {
        return baseMapper.findByPermissionId(permissionId);
    }
}
