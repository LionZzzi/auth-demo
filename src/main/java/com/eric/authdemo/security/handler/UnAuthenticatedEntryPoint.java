package com.eric.authdemo.security.handler;

import com.eric.authdemo.model.common.Result;
import com.eric.authdemo.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未登录状态下直接请求需要权限的接口处理
 *
 * @author Eric
 * @since 2020/8/26 3:30 下午
 */
@Slf4j
@Component
public class UnAuthenticatedEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        log.info("===== 进入UnAuthenticatedEntryPoint =====");
        log.warn("===== 认证失败 =====");
        ResponseUtil.out(httpServletResponse, Result.fail("认证失败"));
    }
}
