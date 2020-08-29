package com.eric.authdemo.security;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.eric.authdemo.model.SecurityUserDetails;
import com.eric.authdemo.model.domain.Role;
import com.eric.authdemo.model.domain.User;
import com.eric.authdemo.service.RoleService;
import com.eric.authdemo.service.UserService;
import com.github.dozermapper.core.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Eric
 * @since 2020/8/26 3:49 下午
 */
@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private Mapper mapper;
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        log.info("==== 进入UserDetailsServiceImpl ====");
        User user = userService.findByName(name);
        if (ObjectUtil.isNull(user)) {
            throw new UsernameNotFoundException("找不到用户");
        }
        List<Role> roles = roleService.findByUserId(user.getId());
        SecurityUserDetails userDetails = mapper.map(user, SecurityUserDetails.class);
        userDetails.setRoles(roles);
        return userDetails;
    }
}
