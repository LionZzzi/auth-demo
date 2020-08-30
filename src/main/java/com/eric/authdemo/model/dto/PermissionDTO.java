package com.eric.authdemo.model.dto;

import com.eric.authdemo.model.domain.Permission;
import com.eric.authdemo.model.domain.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Eric
 * @since 2020/8/30 21:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionDTO extends Permission {

    /**
     * 拥有的角色
     */
    private List<Role> roles;
}
