package com.eric.authdemo.security.handler;

import com.eric.authdemo.model.common.Result;
import com.eric.authdemo.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eric
 * @since 2020/8/26 4:01 下午
 */
@Slf4j
@Component
public class AuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        log.error("===== 进入AuthenticationFailHandler =====");
        log.error("===== 登陆失败 =====");
        log.error("===== 可以使用redis进行限制登陆 ====");
        ResponseUtil.out(response, Result.fail("登陆失败"));
    }
}
