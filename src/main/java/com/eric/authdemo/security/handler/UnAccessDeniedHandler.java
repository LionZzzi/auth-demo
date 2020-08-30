package com.eric.authdemo.security.handler;

import com.eric.authdemo.model.common.Result;
import com.eric.authdemo.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Eric
 * @since 2020/8/26 3:33 下午
 */
@Slf4j
@Component
public class UnAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        log.info("===== 进入UnAccessDeniedHandler =====");
        log.warn("===== 暂无权限 =====");
        ResponseUtil.out(httpServletResponse, Result.fail("暂无权限"));
    }
}
