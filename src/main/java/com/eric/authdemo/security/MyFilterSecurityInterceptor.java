package com.eric.authdemo.security;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import java.io.IOException;

/**
 * 权限管理过滤器
 * 监控用户行为
 *
 * @author Wision wsd0817@gmail.com.
 */
@Slf4j
@Component
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    private static final String FILTER_APPLIED = "__spring_security_myFilterSecurityInterceptor_filterApplied";

    @Resource
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    @Resource
    public void setMyAccessDecisionManager(MyAccessDecisionManager myAccessDecisionManager) {
        super.setAccessDecisionManager(myAccessDecisionManager);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 确保每个请求仅应用一次过滤器：spring容器托管的GenericFilterBean的bean，都会自动加入到servlet的filter chain，
        // 而WebSecurityConfig中myFilterSecurityInterceptor定义的bean还额外把filter加入到了spring security中，所以会出现执行两次的情况。
        // 继承OncePerRequestFilter 也只会执行一次
        if (ObjectUtil.isNotNull(servletRequest.getAttribute(FILTER_APPLIED))) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        servletRequest.setAttribute(FILTER_APPLIED, Boolean.TRUE);
        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);
        invoke(fi);
    }

    /**
     * 调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
     * 再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
     *
     * @param fi 被拦截的url
     * @throws IOException      IO异常
     * @throws ServletException Servlet异常
     */
    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            //执行下一个拦截器
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return securityMetadataSource;
    }
}
