package com.eric.authdemo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author Eric
 * @since 2020/8/30 21:35
 */
@Slf4j
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    /**
     * 判定是否拥有权限的决策方法
     *
     * @param authentication   添加到 SecurityUserDetails GrantedAuthority 对象中的权限信息集合
     * @param o                包含客户端发起的请求的requset信息， HttpServletRequest request = ((FilterInvocation) object).getHttpRequest()
     * @param configAttributes 为MySecurityMetadataSource的getAttributes(Object object)这个方法返回的结果，
     *                         此方法是为了判定用户请求的url 是否在权限表中，
     *                         如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行
     * @throws AccessDeniedException               AccessDenied异常
     * @throws InsufficientAuthenticationException InsufficientAuthentication异常
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        log.info("===== 进入MyAccessDecisionManager decide =====");
        String needRole;
        for (ConfigAttribute configAttribute : configAttributes) {
            needRole = configAttribute.getAttribute();
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (needRole.trim().equals(authority.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("您没有访问权限");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
