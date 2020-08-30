package com.eric.authdemo.security;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.eric.authdemo.model.domain.Role;
import com.eric.authdemo.model.dto.PermissionDTO;
import com.eric.authdemo.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Eric
 * @since 2020/8/30 21:48
 */
@Slf4j
@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Resource
    private PermissionService permissionService;

    AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 加载权限表中所有权限
     */
    public Map<String, Collection<ConfigAttribute>> loadAllPermission() {
        Map<String, Collection<ConfigAttribute>> map = MapUtil.newHashMap();
        Collection<ConfigAttribute> configAttributes;
        List<PermissionDTO> permissions = permissionService.findAll();
        for (PermissionDTO permission : permissions) {
            configAttributes = new ArrayList<>();
            for (Role role : permission.getRoles()) {
                ConfigAttribute cfg = new SecurityConfig(role.getName());
                //作为MyAccessDecisionManager类的decide的第三个参数
                configAttributes.add(cfg);
            }
            //用权限的path作为map的key，用ConfigAttribute的集合作为value
            map.put(permission.getUrl(), configAttributes);
        }
        return map;
    }

    /**
     * 判定用户请求的url是否在权限表中
     *
     * @param o 包含用户请求的request 信息
     * @return Collection<ConfigAttribute>
     * @throws IllegalArgumentException 不合法的参数
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        log.info("===== 进入MySecurityMetadataSource getAttributes =====");
        Map<String, Collection<ConfigAttribute>> map = loadAllPermission();
        // 获取当前请求url
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        log.info("===== 用户当前请求url:{} =====", requestUrl);
        for (String url : map.keySet()) {
            if (StrUtil.isNotBlank(url) && pathMatcher.match(url, requestUrl)) {
                return map.get(url);
            }
        }
        return SecurityConfig.createList("ROLE_NO_ACCESS");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
