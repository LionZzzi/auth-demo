package com.eric.authdemo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eric.authdemo.dao.PermissionMapper;
import com.eric.authdemo.model.domain.Permission;
import com.eric.authdemo.model.dto.PermissionDTO;
import com.eric.authdemo.service.PermissionService;
import com.eric.authdemo.service.RoleService;
import com.eric.authdemo.util.DozerUtil;
import com.github.dozermapper.core.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Eric
 * @since 2020/8/26 4:46 下午
 */
@Slf4j
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Resource
    private Mapper mapper;
    @Resource
    private RoleService roleService;

    @Override
    public List<PermissionDTO> findAll() {
        List<Permission> permissions = baseMapper.selectList(Wrappers.emptyWrapper());
        List<PermissionDTO> dtos = DozerUtil.mapList(mapper, permissions, PermissionDTO.class);
        log.warn("===== 耗性能,仅demo演示用 =====");
        for (PermissionDTO dto : dtos) {
            dto.setRoles(roleService.findByPermissionId(dto.getId()));
        }
        return dtos;
    }
}
