package com.eric.authdemo.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Eric
 * @since 2020/8/26 3:30 下午
 */
@Slf4j
@Component
public class UnAuthenticatedEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        log.warn("===== 认证失败 =====");
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e == null ? "Unauthorized" : e.getMessage());
    }
}
